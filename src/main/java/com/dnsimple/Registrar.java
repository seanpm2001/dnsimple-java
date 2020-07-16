package com.dnsimple;

import com.dnsimple.data.*;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.List;
import java.util.Map;

/**
 * Provides access to the DNSimple Registrar API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/registrar">https://developer.dnsimple.com/v2/registrar</a>
 */
public interface Registrar {
    /**
     * Checks whether a domain is available for registration.
     *
     * @param accountId  The account ID
     * @param domainName The domain to check
     * @return The check domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#check">https://developer.dnsimple.com/v2/registrar/#check</a>
     */
    SimpleResponse<DomainCheck> checkDomain(String accountId, String domainName);

    /**
     * Registers a domain.
     *
     * @param accountId  The account ID
     * @param domainName The domain to register
     * @param attributes Attributes to use for the registration
     * @return The register domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#register">https://developer.dnsimple.com/v2/registrar/#register</a>
     */
    SimpleResponse<DomainRegistration> registerDomain(String accountId, String domainName, Map<String, Object> attributes);

    /**
     * Renews a domain.
     *
     * @param accountId  The account ID
     * @param domainId   The domain name or ID
     * @param attributes Attributes to use for the renewal
     * @return The renew domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#renew">https://developer.dnsimple.com/v2/registrar/#renew</a>
     */
    SimpleResponse<DomainRenewal> renewDomain(String accountId, String domainId, Map<String, Object> attributes);

    /**
     * Starts the transfer of a domain to DNSimple.
     *
     * @param accountId  The account ID
     * @param domainId   The domain name or ID
     * @param attributes Attributes to use for the transfer
     * @return The transfer domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#transfer">https://developer.dnsimple.com/v2/registrar/#transfer</a>
     */
    SimpleResponse<DomainTransfer> transferDomain(String accountId, String domainId, Map<String, Object> attributes);

    /**
     * Retrieves the details of an existing domain transfer.
     *
     * @param accountId        The account ID
     * @param domainId         The domain name or ID
     * @param domainTransferId The domain transfer ID
     * @return The transfer domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#getDomainTransfer">https://developer.dnsimple.com/v2/registrar/#getDomainTransfer</a>
     */
    SimpleResponse<DomainTransfer> getDomainTransfer(String accountId, String domainId, String domainTransferId);

    /**
     * Cancels an in progress domain transfer.
     *
     * @param accountId        The account ID
     * @param domainId         The domain name or ID
     * @param domainTransferId The domain transfer ID
     * @return The transfer domain response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#cancelDomainTransfer">https://developer.dnsimple.com/v2/registrar/#cancelDomainTransfer</a>
     */
    SimpleResponse<DomainTransfer> cancelDomainTransfer(String accountId, String domainId, String domainTransferId);

    /**
     * Requests the transfer of a domain out of DNSimple.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The transfer domain out response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/#transfer-out">https://developer.dnsimple.com/v2/registrar/#transfer-out</a>
     */
    EmptyResponse transferDomainOut(String accountId, String domainId);

    /**
     * Enable auto renewal for the domain in the account.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The enable auto renewal response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/auto-renewal/#enable">https://developer.dnsimple.com/v2/registrar/auto-renewal/#enable</a>
     */
    EmptyResponse enableAutoRenewal(String accountId, String domainId);

    /**
     * Disable auto renewal for the domain in the account.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The disable auto renewal response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/auto-renewal/#disable">https://developer.dnsimple.com/v2/registrar/auto-renewal/#disable</a>
     */
    EmptyResponse disableAutoRenewal(String accountId, String domainId);

    /**
     * Gets the whois privacy for the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The get whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#get">https://developer.dnsimple.com/v2/registrar/whois-privacy/#get</a>
     */
    SimpleResponse<WhoisPrivacy> getWhoisPrivacy(String accountId, String domainId);

    /**
     * Enable whois privacy for the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The enable whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#enable">https://developer.dnsimple.com/v2/registrar/whois-privacy/#enable</a>
     */
    SimpleResponse<WhoisPrivacy> enableWhoisPrivacy(String accountId, String domainId);

    /**
     * Disable whois privacy for the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The disable whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#disable">https://developer.dnsimple.com/v2/registrar/whois-privacy/#disable</a>
     */
    SimpleResponse<WhoisPrivacy> disableWhoisPrivacy(String accountId, String domainId);

    /**
     * Renew whois privacy for the domain.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The disable whois privacy response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#renew">https://developer.dnsimple.com/v2/registrar/whois-privacy/#renew</a>
     */
    SimpleResponse<WhoisPrivacyRenewal> renewWhoisPrivacy(String accountId, String domainId);

    /**
     * Lists name servers the domain is delegating to.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The get domain delegation response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#list">https://developer.dnsimple.com/v2/registrar/delegation/#list</a>
     */
    ListResponse<String> getDomainDelegation(String accountId, String domainId);

    /**
     * Change name servers the domain is delegating to.
     *
     * @param accountId       The account ID
     * @param domainId        The domain ID
     * @param nameServerNames The name server names to change the delegation to
     * @return The change domain delegation response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#update">https://developer.dnsimple.com/v2/registrar/delegation/#update</a>
     */
    ListResponse<String> changeDomainDelegation(String accountId, String domainId, List<String> nameServerNames);

    /**
     * Change the domain delegation to the specified vanity name servers.
     *
     * @param accountId       The account ID
     * @param domainId        The domain ID
     * @param nameServerNames The vanity name server names
     * @return The change domain delegation to vanity response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#delegateToVanity">https://developer.dnsimple.com/v2/registrar/delegation/#delegateToVanity</a>
     */
    ListResponse<VanityNameServer> changeDomainDelegationToVanity(String accountId, String domainId, List<String> nameServerNames);

    /**
     * Change the domain delegation back to the standard DNSimple name servers.
     *
     * @param accountId The account ID
     * @param domainId  The domain ID
     * @return The change domain delegation from vanity response
     * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#delegateFromVanity">https://developer.dnsimple.com/v2/registrar/delegation/#delegateFromVanity</a>
     */
    EmptyResponse changeDomainDelegationFromVanity(String accountId, String domainId);
}
