package Utils;

public class Stock {
    private String code;

    private Integer stockNumber;

    private String batchNumber;

    public Stock() {
    }

    public Stock(String code, Integer stockNumber, String batchNumber) {
        this.code = code;
        this.stockNumber = stockNumber;
        this.batchNumber = batchNumber;
    }

    public String getCode() {
        return code;
    }

    public Integer getStockNumber() {
        return stockNumber;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public static <T> Stock merge(Stock t, Stock t1) {
        t.setStockNumber(t.getStockNumber() + t1.getStockNumber());
        return t;
    }


}

