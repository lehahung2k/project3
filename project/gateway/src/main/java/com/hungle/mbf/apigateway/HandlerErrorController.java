package com.hungle.mbf.apigateway;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HandlerErrorController implements ErrorController {
    private String errorPath="/error";

    public HandlerErrorController() {
    }

    public String getErrorPath() {
        return this.errorPath;
    }

    @RequestMapping(
            value = "/errorPath",
            produces = {"application/vnd.error+json", "application/json"}
    )
    @ResponseBody
    public ResponseEntity error(HttpServletRequest request) {
        int status = this.getErrorStatus(request);
        String errorMessage = this.getErrorMessage(request);

        try {;
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return ResponseEntity.status(status).body(errorMessage);
    }

    private int getErrorStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String getErrorMessage(HttpServletRequest request) {
        Throwable exc = (Throwable)request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "Unexpected error occurred";
    }
}
