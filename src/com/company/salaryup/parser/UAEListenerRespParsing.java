package com.company.salaryup.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UAEListenerRespParsing {
    public static void main(String[] args) {
        /*String[] requestContentGroup = {"0001111222222220224092606.SIF, COMPLETED| 10006128793384,SUCCESS | 10030077910748,SUCCESS | 20009098432239,FAILED : Account closed",
                "0001111222222220224092999.SIF,FAILED	| Invalid Request,Validation error while processing MQ request,",
                "0001111222222220224092003.SIF ,FAILED	| Salary Unsuccessfully Posted By Service,",
                "0001111222222220224093606.SIF,FAILED	| EVP:11 , Income Variable in EDR is not equal to the total Allowances in EVP   | SCR:08,   Total Salary in SCR ( 1100.00 ) doesn't match with the sum EDR total ( 1000.00)",
                "0001111222222220224092505.SIF,REJECTED	| 3,Duplicate Employee Account Number  found |5,Not enough balance for AGT"};
*/

        String[] requestContentGroup = {"0001111222222220224092606.SIF, COMPLETED| XXXX, SUCCESS | 10030077910748,SUCCESS | 20009098432239,FAILED : Account closed",
                "0001111222222220224092999.SIF,FAILED	| Invalid Request",
                "0001111222222220224092003.SIF ,FAILED	| Salary Unsuccessfully Posted By Service,",
                "0001111222222220224093606.SIF,FAILED	| EVP11 , Income Variable in EDR is not equal to the total Allowances in EVP   | SCR:08,   Total Salary in SCR ( 1100.00 ) doesn't match with the sum EDR total ( 1000.00)",
                "0001111222222220224092505.SIF,REJECTED	| 3Duplicate Employee Account Number  found |5,Not enough balance for AGT"};

        //parseResp(requestContentGroup);
        //parseResp_withNullCheck(requestContentGroup);
        //createXML_helloWorld();

        matchZeroLeadInteger();

    }

    private static void matchZeroLeadInteger() {
        //for(String x : new String[]{"ddd", "21", "34.5556", "021y", "0021.778", "21.0.0", "1.012", "0012", "08i7"})
          //  System.out.println(x.matches("\\d+[.]?\\d+"));
            //System.out.println(x.matches("0{0,}21"));

        //System.out.println(Integer.parseInt("123.567"));

        for(String x : new String[]{"21", "34.234", "34.5556", "34.6556",  "0021.778", "21.0", "1.012", "0012"}){
            System.out.println("======= " + x + "=======");
            //System.out.println(Math.abs(Integer.parseInt(x)));
            System.out.println(Math.ceil(Double.parseDouble(x)));
            System.out.println(Math.floor(Double.parseDouble(x)));
            System.out.println(Math.round(Double.parseDouble(x)));
        }

    }

    private static void createXML_helloWorld() {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element rootElement = doc.createElement("response");
            doc.appendChild(rootElement);
            Element header = doc.createElement("header");
            Element body = doc.createElement("body");

            Element filename = doc.createElement("filename");
            Element headerStatus = doc.createElement("status");

            filename.setTextContent("File00002039848.SIF");
            headerStatus.setTextContent("COMPLETED");

            header.appendChild(filename);
            header.appendChild(headerStatus);

            rootElement.appendChild(header);
            rootElement.appendChild(body);


            File xmlFile = new File("uaeResponse.xml");
            FileOutputStream outputStream = new FileOutputStream(xmlFile);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            //StreamResult result = new StreamResult(outputStream);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(bos);
            transformer.transform(source, result);

            outputStream.write(bos.toByteArray());

            BufferedReader reader = new BufferedReader(new FileReader(xmlFile));
            String line = reader.readLine();
            while (line != null){
                System.out.println(line);
                line = reader.readLine();
            }


        } catch (ParserConfigurationException | FileNotFoundException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseResp(String[] requestContentGroup) {

        for(String line : requestContentGroup) {

            System.out.println("\n============================================\n" + line + "\n============================================\n");

            String[] fullData_pipe = line.split("\\|");
            String[] header_comma = (fullData_pipe[0]).split(",");
//            System.out.println(Arrays.stream(fullData_pipe).collect(Collectors.joining("=====")));
//            System.out.println(Arrays.stream(header_comma).collect(Collectors.joining("====")));
            String headerFilename = (header_comma[0]).trim();
            String headerStatus = (header_comma[1]).trim();

            //System.out.println("headerFilename=" + headerFilename + " & headerStatus=" + headerStatus);

            if ("COMPLETED".equals(headerStatus)) {
                int i = 0;
                for (String recordData : fullData_pipe) {
                    if (++i > 1) {
                        String[] record_comma = recordData.split(",");
                        String recordId = (record_comma[0]).trim();
                        String recordStatus = null;
                        String recordError = null;
                        if (record_comma[1].contains(":")) {
                            String[] recordStatusError_colon = record_comma[1].split(":");
                            recordStatus = (recordStatusError_colon[0]).trim();
                            recordError = (recordStatusError_colon[1]).trim();
                        } else {
                            recordStatus = (record_comma[1]).trim();
                        }

                        //System.out.println("recordId=" + recordId + " & recordStatus=" + recordStatus + " & recordError=" + recordError);

                    }
                }
            } else if ("REJECTED".equals(headerStatus)) {
                int i = 0;
                //System.out.println(fullData_pipe[0]);
                for (String recordData : fullData_pipe) {
                    if (++i > 1) {
                        String[] record_comma = recordData.split(",");
                        String lineNumber = (record_comma[0]).trim();
                        String error = (record_comma[1]).trim();
                        //System.out.println("lineNumber=" + lineNumber + " & error=" + error);
                    }
                }
            } else if ("FAILED".equals(headerStatus)) {
                int i = 0;
                for (String recordData : fullData_pipe) {
                    if (++i > 1) {
                        if(recordData.contains(":")){
                            String[] record_pipe = recordData.split("\\|");
                            for(String recordPart : record_pipe){
                                String[] record_comma = recordPart.split(",");
                                String[] typeKeyPair_colon = (record_comma[0]).split(":");
                                String typeKey = (typeKeyPair_colon[0]).trim();
                                String typeErrLineNum = (typeKeyPair_colon[1]).trim();
                                String error = (record_comma[1]).trim();
                                System.out.print("typeKey=" + typeKey + " & typeErrLineNum=" + typeErrLineNum + " & error=" + error);
                            }
                            //System.out.println("");
                        }else {
                            String[] record_comma = recordData.split(",");
                            for(String err : record_comma){
                                String error = (err).trim();
                                System.out.print("Error=" + error + " & ");
                            }
                            //System.out.println("");
                        }
                    }
                }
                //System.out.println("\n");
            }
        }
    }




    private enum ErrorMap {
        E1001("Provided response does not contain any record data (separated with pipe)"),
        E1002("Header should contain filename and status separated with comma"),
        E1003("Filename missing. Header should contain filename and status separated with comma"),
        E1004("Filestatus missing. Header should contain filename and status separated with comma"),
        E1005("Record ID is expected when separated with comma (Expected -> id:status)"),
        E1006("When filestatus=COMPLETED, the record should be separated with comma (Expected -> id,status)"),
        E1007("When filestatus=COMPLETED, the record should be separated with comma (Expected -> id,status)"),
        E1008("When filestatus=COMPLETED, the record status is empty/null (Expected -> status:error)"),
        E1009("When filestatus=COMPLETED, the record status is empty/null (Expected -> status:error)"),
        E1010("When filestatus=COMPLETED, the record status is empty/null (Expected -> id,status)"),
        E1011("When filestatus=REJECTED, (Expected -> linenum,error)"),
        E1012("When filestatus=REJECTED, linenumber missing (Expected -> linenum,error)"),
        E1013("When filestatus=REJECTED, error missing (Expected -> linenum,error)"),
        E1014("When filestatus=FAILED, (Expected -> TYPE:linenum,error)"),
        E1015("When filestatus=FAILED, typeKey missing (Expected -> TYPE:linenum,error)"),
        E1016("When filestatus=FAILED, typeErrLineNum missing (Expected -> TYPE:linenum,error)"),
        E1017("When filestatus=FAILED, error missing (Expected -> TYPE:linenum,error)");

        private String err = null;
        ErrorMap(String err){
            this.err = err;
        }
        private static String findErr(int errCode){
            for(ErrorMap e : values()){
                if(("E"+errCode).equals(e.name()))
                    return e.err;
            }
            return null;
        }
    }



    public static boolean isNullOrEmpty(String str) {
        return (str == null) || (str != null && str.trim().length() == 0);
    }

    public static boolean isNullOrEmpty(String[] strs){
        return (strs == null) || (strs != null && strs.length == 0);
    }

    public static boolean hasLessElementsLogError(String[] strs, int n, int errCode, String original){
        if(isNullOrEmpty(strs) || strs.length < n){
            logError(errCode, original, n);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static void logError(int errCode, String beforeSplit, int n){
        String error = "";
        if(n != -1){
                error ="salary_upload : When split, it should contain at least " + n + " number of elements. " +
                "Error code -> " + errCode + " | Error happened -> " + ErrorMap.findErr(errCode) + " | Problematic part -> " + beforeSplit;
        }else{
            error = "salary_upload : Error code -> " + errCode + " | Error happened -> " + ErrorMap.findErr(errCode) + " | Problematic part -> " + beforeSplit;
        }
        System.out.println(error);
        throw new RuntimeException(error);
    }







    private static void parseResp_withNullCheck(String[] requestContentGroup) {

        for(String line : requestContentGroup) {
            System.out.println("\n============================================\n" + line + "\n============================================\n");
            if(!isNullOrEmpty(line)){
                    String[] fullData_pipe = line.split("\\|");
                            if(hasLessElementsLogError(fullData_pipe, 2, 1001, line)){ //
                                return;
                            }

                    String[] header_comma = (fullData_pipe[0]).split(",");
                            if(hasLessElementsLogError(header_comma, 2, 1002, fullData_pipe[0])){
                                return;
                            }

                    String headerFilename = (header_comma[0]).trim();
                    String headerStatus = (header_comma[1]).trim();

                            if(isNullOrEmpty(headerFilename)) {
                                logError(1003, fullData_pipe[0], -1);
                            }

                            if(isNullOrEmpty(headerStatus)) {
                                logError(1004, fullData_pipe[0], -1);
                            }

                    //System.out.println("headerFilename=" + headerFilename + " & headerStatus=" + headerStatus);
                Response response = new Response();
                Response.Body body = new Response.Body();
                Response.Header header = new Response.Header();
                List<Response.Body.Record> records = new ArrayList<>();

                    if ("COMPLETED".equals(headerStatus)) {
                        int i = 0;
                        for (String recordData : fullData_pipe) {
                            if (++i > 1) {
                                String[] record_comma = recordData.split(",");
                                String recordId = (record_comma[0]).trim();
                                String recordStatus = null;
                                String recordError = null;

                                Response.Body.Record record = new Response.Body.Record();
                                Response.Body.Record.StatusPair statusPair = new Response.Body.Record.StatusPair();
                                record.setRecordId(recordId);

                                        if(isNullOrEmpty(recordId)){
                                            logError(1005, recordData, -1);
                                        }

                                        if(hasLessElementsLogError(record_comma, 2, 1006, recordData)){
                                            return;
                                        }

                                if (record_comma[1].contains(":")) {
                                    String[] recordStatErr_colon = record_comma[1].split(":");

                                            if(hasLessElementsLogError(recordStatErr_colon, 2, 1007, record_comma[1])){
                                                return;
                                            }

                                    recordStatus = (recordStatErr_colon[0]).trim();
                                    recordError = (recordStatErr_colon[1]).trim();
                                            if(isNullOrEmpty(recordStatus)){
                                                logError(1008, recordStatErr_colon[0], -1);
                                            }

                                            if(isNullOrEmpty(recordError)){
                                                logError(1009, recordStatErr_colon[0], -1);
                                            }
                                    statusPair.setStatus(recordStatus);
                                    statusPair.setError(recordError);
                                } else {
                                    recordStatus = (record_comma[1]).trim();
                                    if(isNullOrEmpty(recordStatus)){
                                        logError(1010, recordData, -1);
                                    }
                                    statusPair.setStatus(recordStatus);
                                }

                                //System.out.println("recordId=" + recordId + " & recordStatus=" + recordStatus + " & recordError=" + recordError);

                                record.setStatuspair(statusPair);
                                records.add(record);

                            }
                        }
                    } else if ("REJECTED".equals(headerStatus)) {
                        int i = 0;
                        //System.out.println(fullData_pipe[0]);

                        for (String recordData : fullData_pipe) {
                            if (++i > 1) {
                                String[] record_comma = recordData.split(",");

                                        if(hasLessElementsLogError(record_comma, 2, 1011, recordData)){
                                            return;
                                        }

                                String lineNumber = (record_comma[0]).trim();
                                String error = (record_comma[1]).trim();

                                if(isNullOrEmpty(lineNumber)){
                                    logError(1012, recordData, -1);
                                }

                                if(isNullOrEmpty(error)){
                                    logError(1013, recordData, -1);
                                }

                                Response.Body.Record record = new Response.Body.Record();
                                Response.Body.Record.ErrorPair errorPair = new Response.Body.Record.ErrorPair();

                                //System.out.println("lineNumber=" + lineNumber + " & error=" + error);

                                errorPair.setError(error);
                                errorPair.setLineNumber(Integer.parseInt(lineNumber));
                                record.setErrorpair(errorPair);
                                records.add(record);
                            }
                        }
                    } else if ("FAILED".equals(headerStatus)) {
                        int i = 0;
                        for (String recordData : fullData_pipe) {
                            if (++i > 1) {
                                Response.Body.Record record = new Response.Body.Record();
                                Response.Body.Record.ErrorPair errorPair = new Response.Body.Record.ErrorPair();
                                Response.Body.Record.ErrorPair.TypePair typePair = new Response.Body.Record.ErrorPair.TypePair();
                                if (recordData.contains(":")) {
                                    String[] record_pipe = recordData.split("\\|");
                                    for (String recordPart : record_pipe) {
                                        String[] record_comma = recordPart.split(",");
                                        String[] typeKeyPair_colon = (record_comma[0]).split(":");

                                                if(hasLessElementsLogError(record_comma, 2, 1014, recordPart)){
                                                    return;
                                                }

                                        String typeKey = (typeKeyPair_colon[0]).trim();
                                        String typeErrLineNum = (typeKeyPair_colon[1]).trim();
                                        String error = (record_comma[1]).trim();
                                        //System.out.print("typeKey=" + typeKey + " & typeErrLineNum=" + typeErrLineNum + " & error=" + error);

                                        if(isNullOrEmpty(typeKey)){
                                            logError(1015, recordData, -1);
                                        }

                                        if(isNullOrEmpty(typeErrLineNum)){
                                            logError(1016, recordData, -1);
                                        }

                                        if(isNullOrEmpty(error)){
                                            logError(1017, recordData, -1);
                                        }

                                        typePair.setType(typeKey);
                                        typePair.setLineNumber(Integer.parseInt(typeErrLineNum));
                                        errorPair.setError(error);
                                        errorPair.setTypepair(typePair);
                                        record.setErrorpair(errorPair);
                                        records.add(record);
                                    }
                                    //record.setErrorpair(errorPair);
                                    //records.add(record);
                                    //System.out.println("");
                                } else {
                                    record.setGeneralErrors(Stream.of(recordData.split(",")).map(err -> err.trim()).collect(Collectors.toList()));
                                    records.add(record);
                                }
                            }
                        }
                        //System.out.println("\n");
                    }

                    header.setFilename(headerFilename);
                    header.setStatus(headerStatus);
                    body.setRecords(records);

                    response.setBody(body);
                    response.setHeader(header);


                System.out.println(response);


             }
        }

    }




}
