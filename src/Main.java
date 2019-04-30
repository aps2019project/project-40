import models.Coordination;
import org.omg.CORBA.BAD_CONTEXT;
import request.battleRequest.BattleRequest;
import request.battleRequest.BattleRequestChilds.RequestWithoutVariable;
import request.battleRequest.BattleRequestChilds.SelectAndUseCardRequest;
import request.battleRequest.BattleRequestChilds.ShowCardInfoRequest;
import view.battleView.BattleView;
import view.battleView.GameInfoBattleViewMode1;
import view.battleView.GameInfoBattleViewMode2;
import view.battleView.GameInfoBattleViewMode3;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        BattleRequest battleRequest = BattleRequest.getInstance();
        SelectAndUseCardRequest request = (SelectAndUseCardRequest) battleRequest.getRequest();
        System.out.println(request.isForUse() + " " + request.isForShowInfo());
        System.out.println(request.getColumn() + " " + request.getRow());
        System.out.println(request.getID());
    }
}
