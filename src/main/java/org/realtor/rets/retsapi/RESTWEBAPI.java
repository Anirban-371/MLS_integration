package org.realtor.rets.retsapi;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("/api/rets")
public class RESTWEBAPI {
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<RetsParameters> getPropertylist() {
//		System.out.println("I am here");
		List<RetsParameters> param = new ArrayList<RetsParameters>();
		try {
			param = RETSConnection.getobject();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for (RetsParameters rets : param)
//			System.out.println(rets);
//		String json = new Gson().toJson(param);
//		System.out.println(json);
		return param;
	}
}
