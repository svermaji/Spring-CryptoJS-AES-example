package com.demo.js;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shaiverm on 01-Sep-2015
 */
@RestController
public class DemoController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home () {
        return "home";
    }

    @RequestMapping(value = "/dec", method = RequestMethod.POST, consumes = {"text/plain", "application/*"}, produces = "text/plain;charset=UTF-8")
    public void demoDecrypt (@RequestParam("dataToDec") String data) {
        System.out.println(data + " ----> plaintext = " + decrypt(data));
    }

    /**
     * Parameter input will be in format of string that will be prepared by
     * concatenating parameters - cipherText, iv, salt, passPhrase, keySize,
     * iterationCount in a random order. And at last indices will also be part
     * of this string where 3 random indices will be converted to corresponding
     * alphabet. e.g. 0-represents a , 1-represents b and so on..
     *
     * At 0th position there will always be CipherText At 1st position -> IV At
     * 2nd position -> SALT At 3rd position -> PassPhrase At 4th position ->
     * IterationCount At 5th position -> KeySize At 6th position -> Indices [ in
     * which above six are randomized]
     *
     * @param input ciphered
     * @return String
     */
    public static String decrypt( String input ) {

        String DL = "__bcdef567kop48__";

        String[] values = input.split( DL );
        // last element indicates indices
        String indices = values[values.length - 1];
        int[] indexes = convert( indices );
        SecurityInfo securityInfo = new SecurityInfo( values, indexes );
        SecurityUtil aesUtil = new SecurityUtil( securityInfo.getKeySize(), securityInfo.getIterationCount() );

        return aesUtil.decrypt( securityInfo.getSalt(), securityInfo.getIv(), securityInfo.getPassPhrase(), securityInfo.getCipherText() );

    }

    public static boolean hasValue( String value ) {
        return !StringUtils.isEmpty( value );
    }

    public static int getInt( String s ) {

        if ( StringUtils.isNumeric(s) && hasValue( s ) ) {
            return Integer.parseInt( s );
        }

        return 0;
    }

    private static int[] convert( String indices ) {
        String[] indexes = indices.split( "," );

        int[] ints = new int[indexes.length];

        for ( int i = 0; i < indexes.length; i++ ) {
            String s = getNum( indexes[i] );
            ints[i] = getInt( s );
        }
        return ints;
    }

    private static String getNum( String s ) {
        switch ( s ) {
            case "a":
                return "0";
            case "b":
                return "1";
            case "c":
                return "2";
            case "d":
                return "3";
            case "e":
                return "4";
            case "f":
                return "5";
        }
        return s;
    }

    public static void main(String[] args) {
        String dl = "__bcdef567kop48__";
        // for string "test"
        String s = "%63%38%61%38%33%37%62%33%31%64%2E%32%61%63%38__bcdef567kop48__1000__bcdef567kop48__4b30f6838362407954cb0cb0155f588f__bcdef567kop48__iCQ05Rkus2o/1i/2IaCooQ==__bcdef567kop48__4026c9cffa3567db126b3aaa70d0ea50__bcdef567kop48__128__bcdef567kop48__d,e,1,0,c,5";
        System.out.println("----dl = " + dl+"----s = " + s);
        System.out.println("s = " + decrypt(s));
    }
}
