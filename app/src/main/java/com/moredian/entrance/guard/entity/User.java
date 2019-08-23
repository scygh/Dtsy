package com.moredian.entrance.guard.entity;
import com.moredian.entrance.guard.utils.Cn2Spell;

/**
 * Created by Administrator on 2016/5/25.
 */
public class User implements Comparable<User> {
    private String name; // 姓名
    private String pinyin;
    private String uid;
    // 姓名对应的拼音
    private String firstLetter; // 拼音的首字母

    public User() {
    }

    public User(String name,String uid) {
        this.name = name;
        this.uid = uid;
        pinyin = Cn2Spell.getPinYin(name);
// 根据姓名获取拼音
        firstLetter = pinyin.substring(0, 1).toUpperCase();
// 获取拼音首字母并转成大写
        if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            firstLetter = "#";
        }
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public int compareTo(User another) {
        if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
            return 1;
        } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")) {
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }
    }
}
