package request.shopMenuRequest.shopRequestChilds;

import request.shopMenuRequest.ShopRequest;

public class Show extends ShopRequest {
    private ShopSimpleRequestList shopSimpleRequestList;

    public ShopSimpleRequestList getShopSimpleRequestList() {
        return shopSimpleRequestList;
    }

    public void setShopSimpleRequestList(ShopSimpleRequestList shopSimpleRequestList) {
        this.shopSimpleRequestList = shopSimpleRequestList;
    }
}
