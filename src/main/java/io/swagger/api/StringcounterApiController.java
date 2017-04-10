package io.swagger.api;


import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T07:22:40.307Z")

@Controller
public class StringcounterApiController implements StringcounterApi {



    public ResponseEntity<String> stringcounterGet( @NotNull @ApiParam(value = "Input String", required = true) @RequestParam(value = "inputStr", required = true) String inputStr) {
        return new ResponseEntity<String>(process(inputStr), HttpStatus.OK);
    }

    public ResponseEntity<String> stringcounterPost(@ApiParam(value = "Input String", required=true ) @RequestPart(value="inputStr", required=true)  String inputStr) {
        return new ResponseEntity<String>(process(inputStr), HttpStatus.OK);
    }

    // parse string and return result
    protected String process(String msg) {
        if (msg == null || msg.isEmpty()) {
            return "Test string is invalid";
        }

        int cnt[] = new int[128];
        int maxCnt = 0;
        char maxChar = ' ';

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < msg.length(); ++i) {
            char ch = msg.toLowerCase().charAt(i);
            if (Character.isAlphabetic(ch)) {
                builder.append(msg.charAt(i));
                cnt[ch] += 1;
                if (cnt[ch] > maxCnt) {
                    maxCnt = cnt[ch];
                    maxChar = ch;
                }
            }
        }

        if (builder.length() == 0) {
            return "Test string is invalid";
        }

        return String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d",
                builder.toString(), maxChar, maxCnt);
    }

}
