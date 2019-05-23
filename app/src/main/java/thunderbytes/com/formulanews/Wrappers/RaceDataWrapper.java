package thunderbytes.com.formulanews.Wrappers;

import thunderbytes.com.formulanews.Interfaces.IDataWrapper;

public class RaceDataWrapper implements IDataWrapper {
    public RaceMRDataWrapper MRData;

    public RaceMRDataWrapper getMRData() {
        return MRData;
    }

    public void setMRData(RaceMRDataWrapper MRData) {
        this.MRData = MRData;
    }
}
