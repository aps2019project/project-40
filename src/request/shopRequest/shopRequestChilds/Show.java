package request.shopRequest.shopRequestChilds;

import request.shopRequest.ShopRequest;

public class Show extends ShopRequest {
    private ShopSimpleRequestList shopSimpleRequestList;

    public ShopSimpleRequestList getShopSimpleRequestList() {
        return shopSimpleRequestList;
    }

    public void setShopSimpleRequestList(ShopSimpleRequestList shopSimpleRequestList) {
        this.shopSimpleRequestList = shopSimpleRequestList;
    }
}
