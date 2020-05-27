package com.dnsimple;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.DELETE;
import static com.dnsimple.tools.HttpMethod.GET;
import static com.dnsimple.tools.HttpMethod.PATCH;
import static com.dnsimple.tools.HttpMethod.POST;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import com.dnsimple.data.ZoneRecord;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.response.CreateZoneRecordResponse;
import com.dnsimple.response.DeleteZoneRecordResponse;
import com.dnsimple.response.ListZoneRecordsResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ZoneRecordsTest extends DnsimpleTestBase {

  @Test
  public void testListZoneRecordsSupportsPagination() throws DnsimpleException, IOException {
    client.zones.listZoneRecords("1", "example.com", singletonMap("page", 1));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records?page=1"));
  }

  @Test
  public void testListZoneRecordsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    client.zones.listZoneRecords("1", "example.com", singletonMap("foo", "bar"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records?foo=bar"));
  }

  @Test
  public void testListZoneRecordsSupportsSorting() throws DnsimpleException, IOException {
    client.zones.listZoneRecords("1", "example.com", singletonMap("sort", "name:asc"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records?sort=name:asc"));
  }

  @Test
  public void testListZoneRecordsProducesDomainList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listZoneRecords/success.http");

    List<ZoneRecord> zoneRecords = client.zones.listZoneRecords("1", "example.com").getData();
    assertThat(zoneRecords, hasSize(5));
    assertThat(zoneRecords.get(0).getId(), is(1));
  }

  @Test
  public void testListZoneRecordsExposesPaginationInfo() throws DnsimpleException, IOException {
    server.stubFixtureAt("listZoneRecords/success.http");

    ListZoneRecordsResponse response = client.zones.listZoneRecords("1", "example.com");
    assertThat(response.getPagination().getCurrentPage(), is(1));
  }

  @Test
  public void testGetZoneRecord() throws DnsimpleException, IOException {
    server.stubFixtureAt("getZoneRecord/success.http");

    ZoneRecord record = client.zones.getZoneRecord("1", "example.com", "2").getData();
    assertThat(record.getId(), is(5));
    assertThat(record.getZoneId(), is("example.com"));
    assertThat(record.getParentId(), is(0));
    assertThat(record.getName(), is(""));
    assertThat(record.getContent(), is("mxa.example.com"));
    assertThat(record.getTtl(), is(600));
    assertThat(record.getPriority(), is(10));
    assertThat(record.getType(), is("MX"));
    assertThat(record.getSystemRecord(), is(false));
    assertThat(record.getCreatedAt(), is("2016-10-05T09:51:35Z"));
    assertThat(record.getUpdatedAt(), is("2016-10-05T09:51:35Z"));
  }

  @Test
  public void testGetZoneRecordWhenRecordNotFound() {
    server.stubFixtureAt("notfound-record.http");

    assertThat(() -> client.zones.getZoneRecord("1", "example.com", "2"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testCreateZoneRecordSendsCorrectRequest() throws DnsimpleException, IOException {
    server.stubFixtureAt("createZoneRecord/created.http");

    Map<String, Object> attributes = singletonMap("name", "www");
    client.zones.createZoneRecord("1010", "example.com", attributes);
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/zones/example.com/records"));
    assertThat(server.getRecordedRequest().getHeaders(), Matchers.hasEntry("Content-Type", "application/json"));
    assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
  }

  @Test
  public void testCreateZoneRecordProducesZoneRecord() throws DnsimpleException, IOException {
    server.stubFixtureAt("createZoneRecord/created.http");

    CreateZoneRecordResponse response = client.zones.createZoneRecord("1", "example.com", singletonMap("name", "www"));
    assertThat(response.getData().getId(), is(1));
  }

  @Test
  public void testUpdateZoneRecordProducesZoneRecord() throws DnsimpleException, IOException {
    server.stubFixtureAt("updateZoneRecord/success.http");

    Map<String, Object> attributes = singletonMap("name", "www");
    ZoneRecord record = client.zones.updateZoneRecord("1", "example.com", "2", attributes).getData();
    assertThat(server.getRecordedRequest().getMethod(), is(PATCH));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records/2"));
    assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    assertThat(record.getId(), is(5));
  }

  @Test
  public void testDeleteZoneRecord() throws DnsimpleException, IOException {
    server.stubFixtureAt("deleteZoneRecord/success.http");

    DeleteZoneRecordResponse response = client.zones.deleteZoneRecord("1", "example.com", "2");
    assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/zones/example.com/records/2"));
    assertThat(response.getData(), is(nullValue()));
  }
}
