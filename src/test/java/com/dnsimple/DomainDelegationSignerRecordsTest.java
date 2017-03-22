package com.dnsimple;

import com.dnsimple.data.DelegationSignerRecord;
import com.dnsimple.data.Pagination;
import com.dnsimple.request.Filter;
import com.dnsimple.response.ListDelegationSignerRecordsResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class DomainDelegationSignerRecordsTest extends DnsimpleTestBase {

  @Test
  public void testListDelegationSignerRecordsSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/1010/ds_records?page=1");
    String accountId = "1";
    String domainId = "1010";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.domains.listDelegationSignerRecords(accountId, domainId, options);
  }

  @Test
  public void testListDelegationSignerRecordsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/1010/ds_records?foo=bar");
    String accountId = "1";
    String domainId = "1010";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.domains.listDelegationSignerRecords(accountId, domainId, options);
  }

  @Test
  public void testListDelegationSignerRecordsSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/1010/ds_records?sort=created_at%3Aasc");
    String accountId = "1";
    String domainId = "1010";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "created_at:asc");
    client.domains.listDelegationSignerRecords(accountId, domainId, options);
  }

  @Test
  public void testListDelegationSignerRecordsProducesDelegationSignerRecordList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listDelegationSignerRecords/success.http"));

    String accountId = "1";
    String domainId = "1010";

    ListDelegationSignerRecordsResponse response = client.domains.listDelegationSignerRecords(accountId, domainId);

    List<DelegationSignerRecord> dsRecords = response.getData();
    assertEquals(1, dsRecords.size());
    assertEquals(24, dsRecords.get(0).getId().intValue());
  }

  @Test
  public void testListDelegationSignerRecordsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listDelegationSignerRecords/success.http"));

    String accountId = "1";
    String domainId = "1010";

    ListDelegationSignerRecordsResponse response = client.domains.listDelegationSignerRecords(accountId, domainId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

}
