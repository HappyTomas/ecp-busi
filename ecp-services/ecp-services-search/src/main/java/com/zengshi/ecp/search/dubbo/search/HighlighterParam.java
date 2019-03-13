package com.zengshi.ecp.search.dubbo.search;

/**
 * Created by HDF on 2016/12/13.
 */
public class HighlighterParam {

    private boolean useFastVectorHighlighter=false;

    private int fragsize=100;

    private int snippets=1;

    private EBoundaryScanner boundaryScanner=EBoundaryScanner.BREAKITERATOR;

    private String bsLanguage="zh";

    private String bsCountry="CN";

    private EBsType bsType=EBsType.SENTENCE;

    private int bsMaxScan=10;

    private String bsChars=".,!?。，！？\\t\\n";

    public enum EBsType {

        CHARACTER("CHARACTER"), WORD("WORD"),SENTENCE("SENTENCE"), LINE("LINE");

        private final String name;

        public String getName() {
            return this.name;
        }

        private EBsType(String name) {
            this.name = name;
        }

    }

    public enum EBoundaryScanner {

        SIMPLEBOUNDARYSCANNER("SimpleBoundaryScanner"), BREAKITERATOR("breakIterator");

        private final String name;

        public String getName() {
            return this.name;
        }

        private EBoundaryScanner(String name) {
            this.name = name;
        }

    }

    public EBoundaryScanner getBoundaryScanner() {
        return boundaryScanner;
    }

    public void setBoundaryScanner(EBoundaryScanner boundaryScanner) {
        this.boundaryScanner = boundaryScanner;
    }

    public String getBsChars() {
        return bsChars;
    }

    public void setBsChars(String bsChars) {
        this.bsChars = bsChars;
    }

    public int getBsMaxScan() {
        return bsMaxScan;
    }

    public void setBsMaxScan(int bsMaxScan) {
        this.bsMaxScan = bsMaxScan;
    }

    public EBsType getBsType() {
        return bsType;
    }

    public void setBsType(EBsType bsType) {
        this.bsType = bsType;
    }

    public int getFragsize() {
        return fragsize;
    }

    public void setFragsize(int fragsize) {
        this.fragsize = fragsize;
    }

    public int getSnippets() {
        return snippets;
    }

    public void setSnippets(int snippets) {
        this.snippets = snippets;
    }

    public boolean isUseFastVectorHighlighter() {
        return useFastVectorHighlighter;
    }

    public void setUseFastVectorHighlighter(boolean useFastVectorHighlighter) {
        this.useFastVectorHighlighter = useFastVectorHighlighter;
    }

    public String getBsCountry() {
        return bsCountry;
    }

    public void setBsCountry(String bsCountry) {
        this.bsCountry = bsCountry;
    }

    public String getBsLanguage() {
        return bsLanguage;
    }

    public void setBsLanguage(String bsLanguage) {
        this.bsLanguage = bsLanguage;
    }
}
