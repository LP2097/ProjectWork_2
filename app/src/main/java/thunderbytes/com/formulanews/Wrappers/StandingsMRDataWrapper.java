package thunderbytes.com.formulanews.Wrappers;

public class StandingsMRDataWrapper {
    public StandingsTable StandingsTable;
    public String xmlns;
    public String series;
    public String url;
    public int limit;
    public int offset;
    public int total;

    public StandingsTable getStandingsTable() {
        return StandingsTable;
    }

    public void setRaceTable(StandingsTable standingsTable) {
        this.StandingsTable = standingsTable;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
