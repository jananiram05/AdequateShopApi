package org.adequate.responsePOJO;



import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"email",
"profilepicture",
"location",
"createdat"
})
public class GetDatumPojo {

@JsonProperty("id")
private Integer id;
@JsonProperty("name")
private String name;
@JsonProperty("email")
private String email;
@JsonProperty("profilepicture")
private String profilepicture;
@JsonProperty("location")
private String location;
@JsonProperty("createdat")
private String createdat;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("email")
public String getEmail() {
return email;
}

@JsonProperty("email")
public void setEmail(String email) {
this.email = email;
}

@JsonProperty("profilepicture")
public String getProfilepicture() {
return profilepicture;
}

@JsonProperty("profilepicture")
public void setProfilepicture(String profilepicture) {
this.profilepicture = profilepicture;
}

@JsonProperty("location")
public String getLocation() {
return location;
}

@JsonProperty("location")
public void setLocation(String location) {
this.location = location;
}

@JsonProperty("createdat")
public String getCreatedat() {
return createdat;
}

@JsonProperty("createdat")
public void setCreatedat(String createdat) {
this.createdat = createdat;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}