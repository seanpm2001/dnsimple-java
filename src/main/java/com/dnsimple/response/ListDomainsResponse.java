package com.dnsimple.response;

import com.dnsimple.data.Domain;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListDomainsResponse extends ApiResponse {
  @Key("data")
  private List<Domain> data;

  @Key("pagination")
  private Pagination pagination;

  public List<Domain> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
