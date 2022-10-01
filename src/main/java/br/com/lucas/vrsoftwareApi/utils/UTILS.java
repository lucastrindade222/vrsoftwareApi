package br.com.lucas.vrsoftwareApi.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class UTILS {

   public PageRequest pages(Integer page, Integer linesPerPage, String orderBy, String direction){
       PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
       return pageRequest;
   }

    public static UTILS now(){
        return  new UTILS();
    }

}
