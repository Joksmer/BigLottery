package cn.itedus.lottery.domain.strategy.service;

import java.util.List;
import java.util.Map;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
public class DrawStrategy {

    private final int HASH_INCREMENT = 0x61c88647;

    private String[] rateTuple = new String[128];

    /**
     * 初始化概率索引数组
     *
     * @param drawConfig
     */
    public void initRateTuple(List<Map<String, String>> drawConfig) {
        int cursorVal = 0;
        for (Map<String, String> drawMap : drawConfig) {
            int rateVal = Integer.parseInt(drawMap.get("awardRate"));

            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                // 计算数组索引并填充数据
                int hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
                int idx = hashCode & (rateTuple.length - 1);
                rateTuple[idx] = drawMap.get("awardDesc");
            }

            cursorVal += rateVal;

        }
    }

    public String randomDraw(int rate) {
        int hashCode = rate * HASH_INCREMENT + HASH_INCREMENT;
        int idx = hashCode & (rateTuple.length - 1);
        return rateTuple[idx];
    }

}
