package thunderbytes.com.formulanews.Wrappers;

import thunderbytes.com.formulanews.Interfaces.IDataWrapper;

public class StandingsDataWrapper implements IDataWrapper {
    public StandingsMRDataWrapper MRData;

    public StandingsMRDataWrapper getMRData() {
        return MRData;
    }

    public void setMRData(StandingsMRDataWrapper MRData) {
        this.MRData = MRData;
    }
}
