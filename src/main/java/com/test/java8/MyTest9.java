package com.test.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/3 20:14
 * @Version: 1.0
 */
public class MyTest9 {
    private static Map<String,String> map = new HashMap<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws Exception{
        ProdInstAcctRelDto dto1 = new ProdInstAcctRelDto(1,2,1);
        ProdInstAcctRelDto dto2 = new ProdInstAcctRelDto(-1,1,0);
        ProdInstAcctRelDto dto3 = new ProdInstAcctRelDto(-1,3,1);
        ProdInstAcctRelDto dto4 = new ProdInstAcctRelDto(-1,2,1);
        ProdInstAcctRelDto dto5 = new ProdInstAcctRelDto(-1,4,1);
        List<Predicate<ProdInstAcctRelDto>> filters = Arrays.asList(
                dto->dto.getAcctItemGroupId()!=-1,
                dto->dto.getStatusCd()==1,
                dto->dto.getIfDefaultAcctId()==1
        );
        ProdInstAcctRelDto[] dtos = new ProdInstAcctRelDto[]{dto1,dto2,dto3,dto4,dto5};
        ProdInstAcctRelDto dto = searchBestMatchDto(dtos,filters,0,true);
        System.out.println(dto);
    }

    private static ProdInstAcctRelDto searchBestMatchDto(ProdInstAcctRelDto[] dtos,
                                                         List<Predicate<ProdInstAcctRelDto>> predicates, int depth,boolean isFirstSearch){
        if(isFirstSearch){
            if(dtos.length==0){
                return null;
            }else if(dtos.length==1){
                return dtos[0];
            }
        }
        if(predicates.size()==depth){
            System.out.println("1111111");
            return dtos[0];
        }else{
            ProdInstAcctRelDto[] matchs = Arrays.stream(dtos).filter(predicates.get(depth)).toArray(ProdInstAcctRelDto[]::new);
            depth++;
            if(matchs.length==0){
                return searchBestMatchDto(dtos,predicates,depth,false);
            }else if (matchs.length==1){
                return matchs[0];
            }
            return searchBestMatchDto(matchs,predicates,depth,false);
        }
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class ProdInstAcctRelDto{
        private int acctItemGroupId;
        private int statusCd;
        private int  ifDefaultAcctId;
    }
}
