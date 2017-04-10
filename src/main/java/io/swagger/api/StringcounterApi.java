package io.swagger.api;


import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-10T07:22:40.307Z")

@Api(value = "stringcounter", description = "the stringcounter API")
public interface StringcounterApi {

    @ApiOperation(value = "The service will handle the inputStr, respond and output the alphabet only characters with the original relative sequence, the highest frequent letter(s) and the number(s) of the letter(s) in the string.", notes = "The service will handle the inputStr, respond and output the alphabet only characters with the original relative sequence, the highest frequent letter(s) and the number(s) of the letter(s) in the string. ", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Result of the counter", response = String.class) })
    @RequestMapping(value = "/stringcounter",
        produces = { "text/plain" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> stringcounterGet( @NotNull @ApiParam(value = "Input String", required = true) @RequestParam(value = "inputStr", required = true) String inputStr);


    @ApiOperation(value = "The service will handle the inputStr, respond and output the alphabet only characters with the original relative sequence, the highest frequent letter(s) and the number(s) of the letter(s) in the string.", notes = "The service will handle the inputStr, respond and output the alphabet only characters with the original relative sequence, the highest frequent letter(s) and the number(s) of the letter(s) in the string. ", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Result of the counter", response = String.class) })
    @RequestMapping(value = "/stringcounter",
        produces = { "text/plain" }, 
        method = RequestMethod.POST)
    ResponseEntity<String> stringcounterPost(@ApiParam(value = "Input String", required=true ) @RequestPart(value="inputStr", required=true)  String inputStr);

}
