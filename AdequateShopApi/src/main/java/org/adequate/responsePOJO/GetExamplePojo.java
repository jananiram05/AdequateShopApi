package org.adequate.responsePOJO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"page",
"per_page",
"totalrecord",
"total_pages",
"data"
})
public class GetExamplePojo {

@JsonProperty("page")
private Integer page;
@JsonProperty("per_page")
private Integer perPage;
@JsonProperty("totalrecord")
private Integer totalrecord;
@JsonProperty("total_pages")
private Integer totalPages;
@JsonProperty("data")
private List<GetDatumPojo> data;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("page")
public Integer getPage() {
return page;
}

@JsonProperty("page")
public void setPage(Integer page) {
this.page = page;
}

@JsonProperty("per_page")
public Integer getPerPage() {
return perPage;
}

@JsonProperty("per_page")
public void setPerPage(Integer perPage) {
this.perPage = perPage;
}

@JsonProperty("totalrecord")
public Integer getTotalrecord() {
return totalrecord;
}

@JsonProperty("totalrecord")
public void setTotalrecord(Integer totalrecord) {
this.totalrecord = totalrecord;
}

@JsonProperty("total_pages")
public Integer getTotalPages() {
return totalPages;
}

@JsonProperty("total_pages")
public void setTotalPages(Integer totalPages) {
this.totalPages = totalPages;
}

@JsonProperty("data")
public List<GetDatumPojo> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<GetDatumPojo> data) {
this.data = data;
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