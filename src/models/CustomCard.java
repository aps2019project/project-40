package models;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomCard {
    private Unit setUnit(String name, int hp, int ap, UnitType unitType, int range, int price, Spell... specialPower) {
        Unit unit = new Unit();
        unit.setCardName(name);
        unit.setHP(hp);
        unit.setAP(ap);
        unit.setUnitType(unitType);
        unit.setRange(range);
        for (Spell spell : specialPower)
            unit.addSpell(spell);
        unit.setPrice(price);
        return unit;
    }

    public Unit customHero(String name, int hp, int ap, UnitType unitType, int range, int price, Spell... specialPower) {
        return setUnit(name, hp, ap, unitType, range, price, specialPower);
    }

    public Unit customMinion(String name, int hp, int ap, UnitType unitType, int range, int price, Spell... specialPower) {
        return setUnit(name, hp, ap, unitType, range, price, specialPower);
    }

    public Spell customSpell(String name, CardType type, int price, int coolDown, SpecialPowerType specialPowerType, Target target) {
        Spell spell = new Spell();
        spell.setCardName(name);
        spell.setSpellName(name);
        spell.setType(type);
        spell.setPrice(price);
        spell.setCoolDown(coolDown);
        spell.setSpecialPowerType(specialPowerType);
        spell.setTarget(target);
        return spell;
    }

    public Buff customBuff(String name, String buffType, int effectValue, int delay, boolean lasts) {
        Buff buff = new Buff();
        buff.setName(name);
        if (buffType.equals("holy")) {
            buff.setHoly(effectValue);
        } else if (buffType.equals("poison")) {
            buff.setPoison(effectValue);
        } else if (buffType.equals("power ap")) {
            buff.setWeaknessAP(-effectValue);

        } else if (buffType.equals("power hp")) {
            buff.setWeaknessHP(-effectValue);
        }
        buff.setWaitingTime(delay);
        buff.setLasts(lasts);
        return buff;
    }

    public Target customTarget(int rowsAffected, int columnsAffected,
                               String affectTarget, boolean affectHybrid, boolean affectMelee, boolean affectRanged, String friendOrEnemy) {
        Target target = new Target();
        target.setRowsAffected(rowsAffected);
        target.setColumnsAffected(columnsAffected);
        if (friendOrEnemy.equals("enemy")) {
            target.setTargetEnemy(true);
        }
        if (affectTarget.equals("cells")) {
            target.setAffectCells(true);
        } else if (affectTarget.equals("hero")) {
            target.setAffectHero(true);
        } else if (affectTarget.equals("minion")) {
            target.setAffectMinion(true);
        }
        TargetType targetType = new TargetType(affectHybrid, affectRanged, affectMelee);
        target.setTargetType(targetType);
        return target;
    }
    public void jsonUnit(Unit unit) throws IOException {
        Gson gson = new Gson();
        String fileName =  "./json/customCards" + unit.getCardName() + ".json";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(unit));
        fileWriter.close();
    }
    public void jsonSpell(Spell spell) throws IOException{
        Gson gson = new Gson();
        String fileName =  "./json/customCards" + spell.getCardName() + ".json";
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(spell));
        fileWriter.close();
    }
}
