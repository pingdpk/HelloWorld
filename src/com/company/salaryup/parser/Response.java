package com.company.salaryup.parser;

import java.util.ArrayList;
import java.util.List;


public class Response {

    private Header header;
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header value) {
        this.header = value;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body value) {
        this.body = value;
    }



    public static class Body {

        
    	private List<Record> records;

        public List<Record> getRecords() {
            return records;
        }

        public void setRecords(List<Record> records) {
            this.records = records;
        }

            
        public static class Record {

            
            private String recordId;
            private StatusPair statusPair;
            private List<String> generalErrors;
            private ErrorPair errorPair;

            public String getRecordId() {
                return recordId;
            }

            public void setRecordId(String value) {
                this.recordId = value;
            }

            public StatusPair getStatuspair() {
                return statusPair;
            }

            public void setStatuspair(StatusPair value) {
                this.statusPair = value;
            }

            public List<String> getGeneralErrors() {
                return generalErrors;
            }

            public void setGeneralErrors(List<String> errors) {
                this.generalErrors = errors;
            }

            public ErrorPair getErrorpair() {
                return errorPair;
            }

            public void setErrorpair(ErrorPair value) {
                this.errorPair = value;
            }


            
            public static class ErrorPair {

                
                private TypePair typePair;
                private int lineNumber;
                private String error;

                public TypePair getTypepair() {
                    return typePair;
                }

                public void setTypepair(TypePair value) {
                    this.typePair = value;
                }

                public int getLineNumber() {
                    return lineNumber;
                }

                public void setLineNumber(int value) {
                    this.lineNumber = value;
                }

                public String getError() {
                    return error;
                }

                public void setError(String value) {
                    this.error = value;
                }

                
                public static class TypePair {
                    private String type;
                    private int lineNumber;

                    public String getType() {
                        return type;
                    }

                    public void setType(String value) {
                        this.type = value;
                    }

                    public int getLineNumber() {
                        return lineNumber;
                    }

                    public void setLineNumber(int value) {
                        this.lineNumber = value;
                    }

                    @Override
                    public String toString() {
                        return "TypePair{" +
                                "type='" + type + '\'' +
                                ", lineNumber=" + lineNumber +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "ErrorPair{" +
                            "typePair=" + typePair +
                            ", lineNumber=" + lineNumber +
                            ", error='" + error + '\'' +
                            '}';
                }
            }


            
            public static class GeneralErrors {

                
                private List<String> error;

                public List<String> getError() {
                    if (error == null) {
                        error = new ArrayList<String>();
                    }
                    return this.error;
                }

                public void setError(List<String> value) {
                    this.error = value;
                }

                @Override
                public String toString() {
                    return "GeneralErrors{" +
                            "error=" + error +
                            '}';
                }
            }


            
            public static class StatusPair {

                
                private String status;
                private String error;
                public String getStatus() {
                    return status;
                }

                public void setStatus(String value) {
                    this.status = value;
                }

                public String getError() {
                    return error;
                }

                public void setError(String value) {
                    this.error = value;
                }

                @Override
                public String toString() {
                    return "StatusPair{" +
                            "status='" + status + '\'' +
                            ", error='" + error + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Record{" +
                        "recordId='" + recordId + '\'' +
                        ", statusPair=" + statusPair +
                        ", generalErrors=" + generalErrors +
                        ", errorPair=" + errorPair +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Body{" +
                    "records=" + records +
                    '}';
        }
    }


    
    public static class Header {

        private String filename;
        private String status;
        public String getFilename() {
            return filename;
        }
        public void setFilename(String value) {
            this.filename = value;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String value) {
            this.status = value;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "filename='" + filename + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "Response{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
