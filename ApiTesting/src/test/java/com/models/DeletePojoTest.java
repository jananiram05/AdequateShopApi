package com.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeletePojoTest {
	 @JsonProperty("userid")
		private String userid;
	    
	    @JsonProperty("id")
	    private String id;

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
}
