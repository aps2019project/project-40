package request.accountMenuRequest.accountMenuRequestChilds;

import request.accountMenuRequest.AccountMenuRequest;

public class AccountSimpleRequest extends AccountMenuRequest {
    private AccountOptionList accountOptionList;

    public AccountOptionList getAccountOptionList() {
        return accountOptionList;
    }

    public void setAccountOptionList(AccountOptionList accountOptionList) {
        this.accountOptionList = accountOptionList;
    }
}
