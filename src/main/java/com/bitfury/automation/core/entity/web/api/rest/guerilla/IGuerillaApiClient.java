package com.bitfury.automation.core.entity.web.api.rest.guerilla;

import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.Email;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.Session;
import com.bitfury.automation.core.entity.web.api.rest.guerilla.bean.UserSession;

import java.util.List;

/**
 * Created by FOG on 01.04.2018.
 * <p>
 * Interface to provide basic functions that available by guerilla mail client usage
 */
public interface IGuerillaApiClient {

    /**
     * Create temporary mailbox for specified username.
     * Mailbox will live for quite a long time (approximately 60 min).
     *
     * @return user session object
     */
    public UserSession createTempMailbox(String userName);

    /**
     * Set the email address to a different email address.
     * If the email has already been set, it will be given additional 60 minutes.
     * Otherwise, a new email address will be generated.
     *
     * @param emailUser - ‘local part’ of an email address
     * @param lang      - language code, eg. ‘en’.
     * @param site      - in case you have your own domain and would like to access your.
     * @param session   - object with information about session
     * @return object with information about represented as UserSession
     */
    public UserSession setEmailUser(String emailUser, String lang, String site, Session session);

    /**
     * Check for new email on the server. Returns a list of the newest messages.
     *
     * @param session - session object
     * @param seq     - sequence number (id) of the oldest email.
     * @return list of new emails
     */
    public List<Email> checkEmail(UserSession session, long seq);

    /**
     * Gets messages from the specified offset.
     * Offset of 0 will fetch list of the first 10 emails.
     *
     * @param session - user session object
     * @param offset  - emails to skip. Starts from 0.
     * @param seq     - sequence number(id) of the first email
     * @return list of emails
     */
    public List<Email> getEmailList(UserSession session, long offset, long seq);

    public Session getEmailAddress(String lang, String site, String agent);

    /**
     * Forget the current email address.
     *
     * @param session - user sesion object
     * @return true if successful
     */
    public boolean forgetMe(UserSession session);

}
