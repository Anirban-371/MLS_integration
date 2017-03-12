/*
 * RETSConnection.java
 *
 * Created on November 16, 2001, 1:33 PM
 */
package org.realtor.rets.retsapi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Category;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.realtor.rets.util.RETSConfigurator;

import com.aftexsw.util.bzip.CBZip2InputStream;
import com.google.gson.Gson;

import rets1_5.*;

/**
 * Provides a connection to a RETSServer.
 *
 * @author tweber
 * @version 1.0
 */
public class RETSConnection extends java.lang.Object {
	// log4j category
	static Category cat = Category.getInstance(RETSConnection.class);

	static {
		RETSConfigurator.configure();
	}

	// Key value pairs for request header.
	private HashMap headerHash = new HashMap();
	private HashMap responseHeaderMap = new HashMap();
	private String serverUrl = null;
	private String errMsg = null;
	private boolean isRetryingAuthorization = false;
	private boolean gzipCompressed = false;
	private boolean bzipCompressed = false;
	private boolean STREAMRESPONSE = false;
	private long lastTransactionTime = 0;
	// private String transactionLogDirectory = null;
	private String imageAccept = "image/gif"; // default
	private PrintWriter log = null;
	private HttpClient client = new HttpClient();
	int count = 0;

	HashMap transactionContext = new HashMap(); // holds data across
												// transactions
	private int connTimeoutSeconds = 60; // 60 seconds default

	/**
	 * Creates new RETSConnection and changes default connection timeout and
	 * sets the ServerURL.
	 */
	public RETSConnection(String url, int connTimeoutSeconds) {
		this(url);
		this.connTimeoutSeconds = connTimeoutSeconds;
	}

	/**
	 * Creates new RETSConnection and changes default connection timeout and
	 * sets the ServerURL.
	 */
	public RETSConnection(int connTimeoutSeconds) {
		this();
		this.connTimeoutSeconds = connTimeoutSeconds;
	}

	/**
	 * Creates new RETSConnection and sets the ServerURL.
	 */
	public RETSConnection(String url) {
		this();
		serverUrl = url;
	}

	/**
	 * Create a new RETSConnection and setup some required Header fields.
	 */
	public RETSConnection() {
		setRequestHeaderField("User-Agent", "Mozilla/4.0");
		setRequestHeaderField("RETS-Version", "RETS/1.0");
	}

	/**
	 * Executes a transaction
	 *
	 * @param RETSTransaction
	 *            transaction to execute
	 */
	public void execute(RETSTransaction transaction) {
		execute(transaction, false);
	}

	/**
	 * Executes a transaction
	 *
	 * @param RETSTransaction
	 *            transaction to execute
	 */
	public void executeStreamResponse(RETSTransaction transaction) {
		execute(transaction, true);
	}

	/**
	 * Executes a transaction
	 *
	 * @param RETSTransaction
	 *            transaction to execute
	 */
	public List<RetsParameters> execute(RETSTransaction transaction,
			boolean asStream) {
		java.util.Date dt1 = new Date();
		STREAMRESPONSE = asStream;

		if (transaction instanceof RETSGetObjectTransaction) {
			setRequestHeaderField("Accept", getImageAccept());
		} else {
			setRequestHeaderField("Accept", "*/*");
		}
		// System.out.println("Testing output inside execute");
		/*
		 * transactionLogDirectory = "/media/anirban/D/"; if
		 * ((transactionLogDirectory != null) &&
		 * (transactionLogDirectory.length() > 1)) { String transType =
		 * transaction.getClass().getName(); int nameIdx =
		 * transType.lastIndexOf(".") + 1; String name =
		 * transType.substring(nameIdx); Date dt = new Date(); /*String outFile
		 * = "/media/anirban/D" + "/" + "AAA" + dt.getTime() + ".txt";
		 */

		/*
		 * try { log = new PrintWriter(new FileWriter(outFile));
		 * log.println("<!-- RETS REQUEST -->"); } catch (Exception e) {
		 * e.printStackTrace(); cat.error("could create output file :" +
		 * outFile); }
		 */
		// }*/

		// Handling compression formats of huge bulk data
		String compressFmt = transaction.getCompressionFormat();

		if (compressFmt != null) {
			if (compressFmt.equalsIgnoreCase("gzip")) {
				setRequestHeaderField("Accept-Encoding",
						"application/gzip,gzip");
			} else if (compressFmt.equalsIgnoreCase("bzip")) {
				setRequestHeaderField("Accept-Encoding",
						"application/bzip,bzip");
			} else if (compressFmt.equalsIgnoreCase("none")) {
				removeRequestHeaderField("Accept-Encoding");
			}
		}
		transaction.setContext(transactionContext);

		transaction.preprocess();
		List<RetsParameters> retsparam = new ArrayList<RetsParameters>();
		if (asStream == false) {
			try {
				retsparam = processAndReturnResult_RETSTransaction(transaction);
			} catch (Exception e) {
				e.printStackTrace();
				//do something
			}
		} else {
			processRETSTransaction(transaction);
		}

		transaction.postprocess();

		Date dt2 = new Date();
		lastTransactionTime = dt2.getTime() - dt1.getTime();

		if (log != null) {
			try {
				log.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			log = null;
		}
		// System.out.println("Priniting transaction");
		// System.out.println(transaction);
		return retsparam;
	}

	public long getLastTransactionTime() {
		return lastTransactionTime;
	}

	/*
	 * public void setTransactionLogDirectory(String tLogDir) {
	 * this.transactionLogDirectory = tLogDir; }
	 * 
	 * public String getTransactionLogDirectory() { return
	 * this.transactionLogDirectory; }
	 */

	private void writeToTransactionLog(String msg) {
		if (log != null) {
			try {
				// this.log.println(msg);
				// System.out.println("Printing Contents");
				// System.out.println(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		cat.debug(msg);
	}

	private void writeMapToTransactionLog(Map map) {
		if (map == null) {
			return;
		}

		Iterator itr = map.keySet().iterator();
		Iterator itr1 = map.values().iterator();

		while (itr.hasNext()) {
			String key = (String) itr.next();
			String value = "";
			Object obj = map.get(key);

			if (obj instanceof String) {
				value = (String) obj;
			} else {
				value = "{ ";

				Collection c = (Collection) obj;
				Iterator i2 = c.iterator();

				if (i2.hasNext()) {
					value = (String) i2.next();

					while (i2.hasNext()) {
						value = value + ", " + (String) i2.next();
					}
				}

				value = value + " }";
			}

			writeToTransactionLog(key + "=" + value);
		}
	}

	/**
	 * Returns the server's URL, this url as a base for all transactions
	 */
	public String getServerUrl() {
		return serverUrl;
	}

	/**
	 * Sets the url for the connection.
	 *
	 * @param url
	 *            Server's address ex: http://www.realtor.org/RETSServer
	 */
	public void setServerUrl(String url) {
		serverUrl = url;
	}

	/**
	 * Key value pairs in the client request header
	 *
	 * @param key
	 *            field name in the request header
	 * @param value
	 *            value associated with the key
	 */
	public void setRequestHeaderField(String key, String value) {
		headerHash.put(key, value);
	}

	public void setUserAgent(String userAgent) {
		headerHash.put("User-Agent", userAgent);
	}

	public void setRetsVersion(String retsVersion) {
		setRequestHeaderField("RETS-Version", retsVersion);
	}

	/**
	 * Removes a key/value pair from the request header.
	 *
	 * @param key
	 *            field to remove from the request header.
	 */
	public void removeRequestHeaderField(String key) {
		headerHash.remove(key);
	}

	public HashMap getResponseHeaderMap() {
		return responseHeaderMap;
	}

	/**
	 * gets the url content and returns an inputstream
	 *
	 * @param strURL
	 * @param requestMethod
	 * @param requestMap
	 */
	public InputStream getURLContent(String strURL, String requestMethod,
			Map requestMap) {
		InputStream is = null;
		gzipCompressed = false;
		bzipCompressed = false;

		boolean needToAuth = false;

		HttpMethod method = null;

		cat.debug("getURLContent: URL=" + strURL);
		// System.out.println("StringUrl::"+strURL);
		try {
			if (requestMethod.equalsIgnoreCase("GET")) {
				method = new GetMethod(strURL);
			} else {
				method = new PostMethod(strURL);
			}

			client.getState().setCredentials(
					null,
					null,
					new UsernamePasswordCredentials(getUsername(),
							getPassword()));
			client.getState().setCookiePolicy(CookiePolicy.COMPATIBILITY);
			client.setConnectionTimeout(connTimeoutSeconds * 1000);

			method.setDoAuthentication(true);
			method.setFollowRedirects(true);

			addHeaders(method, headerHash);
			writeMapToTransactionLog(headerHash);

			// send the request parameters
			if (requestMap != null) {
				NameValuePair[] pairs = mapToNameValuePairs(requestMap);
				// System.out.println("Cheking the pairs");

				if (requestMethod.equalsIgnoreCase("POST")) {
					// requestMethod is a post, so we can safely cast.
					PostMethod post = (PostMethod) method;
					post.setRequestBody(pairs);
				} else {
					GetMethod get = (GetMethod) method;
					get.setQueryString(pairs);
				}
			}

			this.writeToTransactionLog("<!-- Response from server -->");

			int responseCode = client.executeMethod(method);
			// System.out.println("Response::"+responseCode);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ByteArrayInputStream bais = new ByteArrayInputStream(
					method.getResponseBody());
			copyResponseHeaders(method);
			method.releaseConnection(); // from bruce
			return bais;
		} catch (IOException io) {
			io.printStackTrace();
			errMsg = "RETSAPI: I/O exception while processing transaction: "
					+ io.getMessage();
			return null;
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
	}

	/**
	 * Changes a map into an array of name value pairs
	 *
	 * @param requestMap
	 *            The map to change.
	 * @return An array of Name value pairs, representing keys and values from
	 *         the map.
	 */
	private NameValuePair[] mapToNameValuePairs(Map requestMap) {
		NameValuePair[] pairs = new NameValuePair[requestMap.size()];
		Iterator iter = requestMap.keySet().iterator();
		int i = 0;

		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = (String) requestMap.get(key);
			NameValuePair nvp = new NameValuePair(key, value);
			pairs[i] = nvp;
			i++;
		}

		return pairs;
	}

	/**
	 * Adds response headers to Http method
	 *
	 * @param responseHeaderMap
	 * @param method
	 */
	private void copyResponseHeaders(HttpMethod method) {
		responseHeaderMap.clear();

		Header[] headers = method.getResponseHeaders();

		for (int i = 0; i < headers.length; i++) {
			Header current = headers[i];
			List list = (List) responseHeaderMap.get(current.getName());

			if (list == null) {
				list = new ArrayList();
			}

			list.add(current.getValue());
			responseHeaderMap.put(current.getName(), list);
		}
	}

	private void addHeaders(HttpMethod method, HashMap headers) {
		Iterator keys = headers.keySet().iterator();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = headers.get(key);

			if (value instanceof String && isValidString((String) value)) {
				method.addRequestHeader(key, (String) value);
			} else if (value instanceof ArrayList) {
				ArrayList list = (ArrayList) value;
				StringBuffer valueList = new StringBuffer();

				for (int i = 0; i < list.size(); i++) {
					if (i > 0) {
						valueList.append(";");
					}

					valueList.append(list.get(i));
				}

				method.addRequestHeader(key, valueList.toString());
			}
		}
	}

	// anirban
	/**
	 * Processes a transaction, sends rets request and gets the response stream
	 * from the server. Uncompresses the response stream if compression was used
	 * in the reply
	 *
	 * @param transaction
	 *            rets transaction to process
	 */
	private List<RetsParameters> processAndReturnResult_RETSTransaction(
			RETSTransaction transaction)  throws Exception{
			serverUrl = transaction.getUrl();

			cat.debug(transaction.getRequestType() + " URL : {" + serverUrl
					+ "}");
			if (serverUrl == null) {
				cat.error(transaction.getRequestType() + " URL is null");
				transaction.setResponseStatus("20036");
				transaction.setResponseStatusText(transaction.getRequestType()
						+ " URL is missing. Successful login is required.");
				throw new RuntimeException(
						"URL is missing. Successful login is required.");
			}

			String method = "POST";

			// Action transaction requires a GET according to RETS spec
			if (transaction.getRequestType().equalsIgnoreCase("Action")) {
				method = "GET";
			}
			cat.debug("method: " + method);
			InputStream is = getURLContent(serverUrl, method,
					transaction.getRequestMap());

			if (is == null) {
				transaction.setResponseStatus("20513"); // Miscellaneous error
				transaction.setResponseStatusText(errMsg);
				transaction.setResponse(errMsg);
				errMsg = null;
				// action to trace the problem
				throw new RuntimeException("Errorcode 20513");
			} else {
				Object compressionFmt = responseHeaderMap
						.get("Content-Encoding");
				if (compressionFmt != null) {
					cat.debug("Header class : "
							+ compressionFmt.getClass().getName());
					if (compressionFmt.toString().equalsIgnoreCase("[gzip]")) {
						gzipCompressed = true;
					} else if (compressionFmt.toString().equalsIgnoreCase(
							"[bzip]")) {
						bzipCompressed = true;
					}
				}
				if (gzipCompressed) {
					is = new GZIPInputStream(is);
				} else if (bzipCompressed) {
					is = new CBZip2InputStream(is);
				}
			}
			transaction.setResponseHeaderMap(this.responseHeaderMap);
			if ((transaction instanceof RETSGetObjectTransaction && (!transaction
					.getResponseHeader("Content-Type").startsWith("text/xml")))
					|| STREAMRESPONSE) {
				transaction.setResponseStream(is);
			} else {
				String contents = null;
				contents = streamToString(is);
				// writeToTransactionLog(contents);
				/*
				 * catch( IOException e) { errMsg =
				 * "Error reading response stream: " + contents;
				 * cat.error(errMsg, e); transaction.setResponseStatus("20513");
				 * // Miscellaneous error
				 * transaction.setResponseStatusText(errMsg); errMsg = null; }
				 */
				if (contents.length() == 0) {
					transaction.setResponseStatus("20513"); // Miscellaneous
					transaction.setResponseStatusText("Empty Body");
					throw new RuntimeException("No response obtained");
				}

				transaction.setResponse(contents);
				List<RetsParameters> retsparamslist = new ArrayList<RetsParameters>();
				JAXBContext jaxbContext = JAXBContext.newInstance(RETS.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				RETS rets = (RETS) jaxbUnmarshaller.unmarshal(new StringReader(
						contents));
				// <!ELEMENT REData (REProperties?, REOffices?, REAgents?,
				// REOfficeRosters?, REProspects?, REActivities?, REHistories?,
				// REPublicRecords?)>
				if (rets != null && rets.getReData() != null
					&& rets.getReData().getREProperties() != null) {
				REProperties reproperties = rets.getReData()
						.getREProperties();
				// <!ELEMENT REProperties (CopyrightNotice?, Disclaimer?,
				// ResidentialProperty*, CommonInterest*, LotsAndLand*,
				// MultiFamily*, TaxData*)>
				if (reproperties.getResidentialProperty() != null) {
					for (ResidentialProperty residentialproperty : reproperties
							.getResidentialProperty()) {
						retsparamslist.add(process_residential_property(
								residentialproperty, reproperties));
					}
				}
				if (reproperties.getLotsAndLand() != null) {
					LotsAndLand lotsandland = new LotsAndLand();
					if (lotsandland.getListing() != null) {
						// /go deep following dtd
					}
				}
				if (reproperties.getMultiFamily() != null) {
					// /go deep following dtd
				}
				if (reproperties.getCommonInterest() != null) {
					for (CommonInterest commoninterest : reproperties
							.getCommonInterest()) {
						if (commoninterest.getResidentialProperty() != null) {
							retsparamslist.add(process_residential_property(
									commoninterest.getResidentialProperty(), reproperties));
						}
					}
				}
				return retsparamslist;
			}
		}
		return null;
	}

	// anirban
	public RetsParameters process_residential_property(
			ResidentialProperty residentialproperty, REProperties reproperties) {
		RetsParameters retsparams = new RetsParameters();
		// make this json
		Gson jsonObj = new Gson();
		retsparams.house_category = "Residential";
		retsparams.house_type = "Apartment";
		retsparams.property_data = jsonObj.toJson(residentialproperty);// TASK-property_data
		if (residentialproperty.getListing() != null) { // is listing data
														// available?????
			Listing listing = residentialproperty.getListing();
			if (listing.getStreetAddress() != null) {

				StreetAddress streetAddress = listing.getStreetAddress();
				retsparams = getCorrectAddress(
						streetAddress
								.getStreetNumberOrBoxNumberOrStreetDirPrefixOrStreetNameOrStreetAdditionalInfoOrStreetDirSuffixOrStreetSuffixOrUnitNumberOrCityOrStateOrProvinceOrCountryOrPostalCodeOrCarrierRouteOrUnstructured(),
						retsparams);
				// add the others following the dtd
				if (listing.getListingData() != null) {
					ListingData listingData = listing.getListingData();
					if (listingData.getListPrice() != null) {
						ListPrice listPrice = listingData.getListPrice();
						if (listPrice.getvalue() != null) {
							retsparams.property_price = listPrice.getvalue();
						}
						if (listPrice.getCurrencyCode() != null) {
							retsparams.property_currency = listPrice
									.getCurrencyCode();
						}
					}
					if (listingData.getExpirationDate() != null) {
						ExpirationDate expirationDate = listingData
								.getExpirationDate();
						if (expirationDate.getvalue() != null) {
							retsparams.property_expriry_date = expirationDate
									.getvalue();
						}
					}

				}
				if (listing.getListingID() != null) {
					ListingID listingID = listing.getListingID();
					retsparams.mls_id = listingID.getvalue();
				}
			}
			if (residentialproperty.getBedrooms() != null) {
				Bedrooms bedrooms = residentialproperty.getBedrooms();
				retsparams.parameters_total_bedrooms = bedrooms.getvalue();
			}
			if (residentialproperty.getBaths() != null) {
				Baths baths = residentialproperty.getBaths();
				retsparams.parameters_total_bedrooms = baths.getBathsFull()
						.toString();
			}
			if (residentialproperty.getLivingArea() != null) {
				LivingArea livingArea = residentialproperty.getLivingArea();
				if (livingArea.getArea() != null) {
					Area area = livingArea.getArea();
					retsparams.area_square_feet = Integer.parseInt(area
							.getvalue());
				}
			}
			if (residentialproperty.getYearBuilt() != null) {
				YearBuilt yearBuilt = residentialproperty.getYearBuilt();
				if (yearBuilt.getvalue() != null) {
					retsparams.parameters_year_built = yearBuilt.getvalue();
				}
			}
			// more to go

		}
		return retsparams;
	}

	private RetsParameters getCorrectAddress(List<Object> address,
			RetsParameters retsparams) {
		System.out.println("Inside getCorrectAddress");
		String streetNo = null;
		String boxnum = null;
		String streetdirPrefix = null;
		String streetName = null;
		String streetAdditionalInfo = null;
		String streetDirSuffix = null;
		String streetSuffix = null;

		for (Object o : address) {
			if (o instanceof UnitNumber) {
				retsparams.house_number = ((UnitNumber) o).getvalue();
			}
			if (o instanceof BoxNumber) {
				boxnum = ((BoxNumber) o).getvalue();
			}
			if (o instanceof StreetDirPrefix) {
				streetdirPrefix = ((StreetDirPrefix) o).getvalue();
			}
			if (o instanceof StreetName) {
				streetName = ((StreetName) o).getvalue();
			}
			if (o instanceof StreetAdditionalInfo) {
				streetAdditionalInfo = ((StreetAdditionalInfo) o).getvalue();
			}
			if (o instanceof StreetDirSuffix) {
				streetDirSuffix = ((StreetDirSuffix) o).getvalue();
			}
			if (o instanceof StreetSuffix) {
				streetSuffix = ((StreetSuffix) o).getvalue();
			}
			if (o instanceof City) {
				retsparams.address_city = ((City) o).getvalue();
			}
			if (o instanceof StateOrProvince) {
				retsparams.address_state = ((StateOrProvince) o).getvalue();
			}
			if (o instanceof Country) {
				retsparams.address_country = ((Country) o).getvalue();
			}
			if (o instanceof PostalCode) {
				retsparams.address_zip_postal_code = ((PostalCode) o)
						.getvalue();
			}

		}
		return retsparams;
	}

	/**
	 * Processes a transaction, sends rets request and gets the response stream
	 * from the server. Uncompresses the response stream if compression was used
	 * in the reply
	 *
	 * @param transaction
	 *            rets transaction to process
	 */
	private void processRETSTransaction(RETSTransaction transaction) {
		try {
			serverUrl = transaction.getUrl();

			cat.debug(transaction.getRequestType() + " URL : {" + serverUrl
					+ "}");
			// System.out.println("Test>" + transaction.getRequestType() +
			// " URL : {" + serverUrl + "}");
			if (serverUrl == null) {
				cat.error(transaction.getRequestType() + " URL is null");
				transaction.setResponseStatus("20036");
				transaction.setResponseStatusText(transaction.getRequestType()
						+ " URL is missing. Successful login is required.");
				return; // throw exception here
			}

			String method = "POST";

			// Action transaction requires a GET according to RETS spec
			if (transaction.getRequestType().equalsIgnoreCase("Action")) {
				method = "GET";
			}
			cat.debug("method: " + method);
			// System.out.println("serverUrl:::"+serverUrl);
			// System.out.println("method:::"+method);
			// System.out.println("transaction.getRequestMap():::"+transaction.getRequestMap());
			InputStream is = getURLContent(serverUrl, method,
					transaction.getRequestMap());

			if (is == null) {
				// System.out.println("Test> is is null");
				transaction.setResponseStatus("20513"); // Miscellaneous error
				transaction.setResponseStatusText(errMsg);
				transaction.setResponse(errMsg);
				errMsg = null;

				return;
			} else {
				Iterator itr = responseHeaderMap.keySet().iterator();
				Iterator itr1 = responseHeaderMap.values().iterator();

				Object compressionFmt = responseHeaderMap
						.get("Content-Encoding");

				if (compressionFmt != null) {
					cat.debug("Header class : "
							+ compressionFmt.getClass().getName());

					if (compressionFmt.toString().equalsIgnoreCase("[gzip]")) {
						gzipCompressed = true;
					} else if (compressionFmt.toString().equalsIgnoreCase(
							"[bzip]")) {
						bzipCompressed = true;
					}
				}

				if (gzipCompressed) {
					is = new GZIPInputStream(is);
				} else if (bzipCompressed) {
					is = new CBZip2InputStream(is);
				}
			}
			this.writeToTransactionLog("<!-- Obtained and Identified Response Stream -->");

			transaction.setResponseHeaderMap(this.responseHeaderMap);

			if ((transaction instanceof RETSGetObjectTransaction && (!transaction
					.getResponseHeader("Content-Type").startsWith("text/xml")))
					|| STREAMRESPONSE) {
				// System.out.println("Transaction instance if");
				transaction.setResponseStream(is);
			} else {
				// System.out.println("Transaction instance else");
				String contents = null;
				// System.out.println("contents1="+contents);
				contents = streamToString(is);
				// System.out.println("contents="+contents);
				writeToTransactionLog(contents);

				/*
				 * catch( IOException e) { errMsg =
				 * "Error reading response stream: " + contents;
				 * cat.error(errMsg, e); transaction.setResponseStatus("20513");
				 * // Miscellaneous error
				 * transaction.setResponseStatusText(errMsg); errMsg = null; }
				 */
				if (contents.length() == 0) {
					transaction.setResponseStatus("20513"); // Miscellaneous
															// error
					transaction.setResponseStatusText("Empty Body");
				}

				transaction.setResponse(contents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String getUsername() {
		String username = null; // (String)requestMap.get("username");

		if (username == null) {
			username = (String) transactionContext.get("username");
		}

		return username;
	}

	String getPassword() {
		String password = null; // (String)requestMap.get("password");

		if (password == null) {
			password = (String) transactionContext.get("password");
		}

		return password;
	}

	/**
	 * Removes the quotes on a string.
	 *
	 * @param quotedString
	 *            string that might contain quotes
	 */
	private static String removeQuotes(String quotedString) {
		if ((quotedString != null) && (quotedString.length() > 2)) {
			return quotedString.substring(1, quotedString.length() - 1);
		} else {
			return ""; // empty string
		}
	}

	/**
	 * Checks to make sure the string passed in is a valid string parameter (not
	 * null and not zero length).
	 *
	 * @param value
	 *            string to be validated
	 */
	private boolean isValidString(String value) {
		return ((value != null) && (value.length() > 0));
	}

	private String streamToString(InputStream is) throws IOException {
		// System.out.println("IS IS:"+is);
		if (is != null) {
			StringBuffer sb = new StringBuffer();
			int numread = 0;
			byte[] buffer = new byte[1024 * 8]; // initialize an 8k buffer

			while ((numread = is.read(buffer)) >= 0) {
				String s = new String(buffer, 0, numread);
				sb.append(s);
			}

			return sb.toString();
		}

		return null;
	}

	/**
	 * Main method for testing only!
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static List<RetsParameters> getobject() {
		BasicConfigurator.configure();

		RETSConnection rc = new RETSConnection();
		RETSLoginTransaction trans = new RETSLoginTransaction();
		RETSSearchPropertyTransaction prop_search_trans = new RETSSearchPropertyTransaction();
		List<RetsParameters> retsparam = new ArrayList<RetsParameters>();
		try {
			rc.setServerUrl("http://agdb.rets.interealty.com/Login.asmx/Login");
			rc.setRetsVersion("RETS/1.5");
			rc.setUserAgent("ACTRISIDX/1.0");
			rc.setUserAgentPassword("321654");
			trans.setUrl("http://agdb.rets.interealty.com/Login.asmx/Login");
			trans.setUsername("ACTRISBETA1");
			trans.setPassword("1BetaOne");
			prop_search_trans.setSearchClass("PROPERTY");// Anirban
			prop_search_trans.setQuery(" (Fireplaces = 9)");
			rc.execute(trans);// login
			retsparam = rc.execute(prop_search_trans, false);
			// System.out.println("Received");
			// for(RetsParameters rets:retsparam )
			// System.out.println(rets);
			// rc.execute(transaction);
			// prop_search_trans.setSearchSelect("Listing_Price%3D100000%2B");
			// Map<String, String> map = new HashMap<String, String>();
			// http://agdb.rets.interealty.com/Search.asmx/Search?Class=PROPERTY&Format=COMPACT
			// &Query=%28FirePlaces+%3D+1%29&QueryType=DMQL2&SearchType=Property
			// Class=PROPERTY&Format=COMPACT&Query=%28FirePlaces+%3D+1%29&QueryType=DMQL2&SearchType=Property
			// / SearchType=Property&Class=PROPERTY&FirePlaces=9
			// map.put("Class", "PROPERTY");
			// map.put("Format", "COMPACT");
			// map.put("SearchType", "Property");
			// String str = buildQueryString(map);
			// System.out.println("Query String is::" + str);
			// prop_search_trans.setQuery(str);
			// System.out.println(trans.getResponse());
			return retsparam;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setUserAgentPassword(String userAgentPassword) {
		headerHash.put("ua_pwd", userAgentPassword);

	}

	/**
	 * Build the queryString from the request map
	 *
	 * @param requestMap
	 *            the list of request parameters
	 */
	private static String buildQueryString(Map requestMap) {

		// if
		// (((String)(requestMap.get("requestType"))).equalsIgnoreCase("Search"
		// )) { return
		// "SearchType=Property&Class=RESI&Query=(FirePlaces%3D9)&QueryType=DMQL"
		// ; }
		// System.out.println("Inside buildQueryString");
		StringBuffer sb = new StringBuffer();
		Iterator it = requestMap.keySet().iterator();

		// build query string
		while (it.hasNext()) {
			String key = (String) it.next();

			if (key.equals("requestType")) {
				// commenting out requestType because it is not a standard req
				// parameter and may break RETS servers
				continue;
			}

			String reqStr = key + "="
					+ URLEncoder.encode((String) requestMap.get(key));
			cat.debug(reqStr);
			sb.append(reqStr);

			if (it.hasNext()) {
				sb.append("&");
			}
		}

		return sb.toString();
	}

	public String getImageAccept() {
		return imageAccept;
	}

	public void setImageAccept(String imageAccept) {
		this.imageAccept = imageAccept;
	}
}
