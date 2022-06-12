package za.co.uyanda.interview.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRAIN 85
 */
public interface ProcessRequest {
    boolean processTheRequest(HttpServletRequest req, HttpServletResponse res);
    void processTheResponse(HttpServletRequest req, HttpServletResponse res);
}
