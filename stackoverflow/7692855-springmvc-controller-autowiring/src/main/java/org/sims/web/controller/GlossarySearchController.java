package org.sims.web.controller;

import org.sims.service.SIMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 10/8/11
 * Time: 3:37 PM
 */
@Controller
@RequestMapping(value = "/glossarySearchResults")
public class GlossarySearchController {
    @Autowired
    private SIMSService simsService = null;

    @RequestMapping(method = RequestMethod.GET)
    public void get(ServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().print("My SIMS service: " + simsService);
    }
}