package io.swagger.api;


import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T07:22:40.307Z")

@Controller
public class StringcounterApiController implements StringcounterApi {
    protected static final String INVALID_REQUEST_STRING = "Invalid request string";


    public ResponseEntity<String> stringcounterGet( @NotNull @ApiParam(value = "Input String", required = true) @RequestParam(value = "inputStr", required = true) String inputStr) {
        String retVal = process(inputStr);
        HttpStatus status = (retVal == INVALID_REQUEST_STRING) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<String>(retVal, status);
    }

    @Override
    public ResponseEntity<String> stringcounterPostJson(@ApiParam(value = "Input String", required=true ) @RequestBody(required = true) InputStr inputStr) {
        String retVal = process(inputStr.getInputStr());
        retVal = "{\"result\":" + quote(retVal) + "}";
        HttpStatus status = (retVal == INVALID_REQUEST_STRING) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<String>(retVal, status);
    }

    public ResponseEntity<String> stringcounterPost(@ApiParam(value = "Input String", required=true ) @RequestPart(value="inputStr", required=true)  String inputStr) {
        String retVal = process(inputStr);
        HttpStatus status = (retVal == INVALID_REQUEST_STRING) ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<String>(retVal, status);
    }

    // parse string and return result
    protected String process(String msg) {
        if (msg == null || msg.isEmpty()) {
            return INVALID_REQUEST_STRING;
        }

        int cnt[] = new int[128];
        int maxCnt = 0;
        char maxChar = ' ';

        // StringBuilder is more efficient than String
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
            return INVALID_REQUEST_STRING;
        }

        return String.format("The letters are: ‘%1$s’. The most frequent letter is ‘%2$s’, and the frequency is %3$d",
                builder.toString(), maxChar, maxCnt);
    }

    // http://stackoverflow.com/questions/3020094/how-should-i-escape-strings-in-json
    public static String quote(String string) {
        if (string == null || string.length() == 0) {
            return "\"\"";
        }

        char         c = 0;
        int          i;
        int          len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String       t;

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    //                if (b == '<') {
                    sb.append('\\');
                    //                }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }

}
