package com.jeffery.sparsearray;

import org.junit.Before;
import org.junit.Test;

/**
 * @description: 稀疏数组
 * @date: 2020/3/29 14:00
 * @author: jeffery
 */
public class SparseArray {
    private int[][] oriArr = new int[15][15];

    @Before
    public void initOriArr(){
        oriArr[3][3] = 1;
        oriArr[4][4] = 2;

        System.out.println("原始数组：");
        printArr(oriArr);
    }

    /**
     * 输出数组
     *
     * @param array
     */
    private void printArr(int[][] array){
        for (int[] arrayTemp : array) {
            for (int i : arrayTemp) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Test
    public void test(){
        System.out.println( "==> 原始数组转稀疏数组" );
        int[][] sparseArr = toSparseArr(oriArr);
        printArr(sparseArr);
        System.out.println( "==> 稀疏数组转原始数组" );
        int[][] oriArr = toOriArr(sparseArr);
        printArr(oriArr);
    }

    /**
     * 二维数组转稀疏数组
     *
     * @return
     */
    private int[][] toSparseArr(int[][] oriArr){
        // 遍历获取有效元素个数
        int counts = 0;
        for (int[] arrayTemp : oriArr) {
            for (int i : arrayTemp) {
                if( i != 0 ){
                    counts ++;
                }
            }
        }

        // 初始化稀疏数组
        int sparseArr[][] = new int[counts + 1][3];
        // 首行
        // 原始数组行数
        sparseArr[0][0] = oriArr.length;
        // 原始数组列数
        sparseArr[0][1] = oriArr[0].length;
        // 原始数组有效个数
        sparseArr[0][2] = counts;

        int start = 0;
        for (int i = 0; i < oriArr.length; i++) {
            for (int j = 0; j < oriArr[i].length; j++) {
                if( oriArr[i][j] != 0 ){
                    start ++;
                    sparseArr[start][0] = i;
                    sparseArr[start][1] = j;
                    sparseArr[start][2] = oriArr[i][j];
                }
            }
        }
        return sparseArr;
    }

    /**
     * 稀疏数组转原始数组
     *
     * @return
     */
    private int[][] toOriArr(int[][] sparseArr){
        // 第1行为原始数组结构
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int[][] oriArrTemp = new int[row][col];
        for (int i = 1; i < sparseArr.length; i++) {
            oriArrTemp[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return oriArrTemp;
    }

}
