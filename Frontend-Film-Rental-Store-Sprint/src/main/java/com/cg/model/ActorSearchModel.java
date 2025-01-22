package com.cg.model;

import java.util.Objects;

public class ActorSearchModel {
    private String searchTerm;
    private String searchType = "default";
 
    public String getSearchTerm() {
        return searchTerm;
    }
 
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
 
    public String getSearchType() {
        return searchType;
    }
 
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

	@Override
	public int hashCode() {
		return Objects.hash(searchType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActorSearchModel other = (ActorSearchModel) obj;
		return Objects.equals(searchType, other.searchType);
	}
    
    
}