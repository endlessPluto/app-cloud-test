package com.nwp.util;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * List按照指定字段排序工具类 
 */
@Log4j2
public class ListSortUtil<T> {

	/** 
     * @param targetList 目标排序List 
     * @param sortField 排序字段(实体类属性名) 
     * @param sortMode 排序方式（asc or  desc） 
     */  
	public void sort(List<T> targetList, final String sortField, final String sortMode) {  
        Collections.sort(targetList, new Comparator<T>() {
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    //首字母转大写  
                    String newStr=sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");   
                    String methodStr="get"+newStr;
                    Method method1 = obj1.getClass().getMethod(methodStr);
                    Method method2 = obj2.getClass().getMethod(methodStr);
                    Double int1=0d,int2=0d;
                    try{
	                    int1=Double.parseDouble(method1.invoke(obj1).toString());
	                    int2=Double.parseDouble(method2.invoke(obj2).toString());
	                    
	                    if(int1.equals(int2)){
	                    	return 0;
	                    }
	                    if (sortMode != null && "desc".equals(sortMode)) {
	                    	retVal=int1>int2?-1:1;
	                    } else {
	                    	retVal=int1>int2?1:-1;
	                    }
                    }catch(Exception e){
                    	//转型错误时按照字符串排序
                    	 if (sortMode != null && "desc".equals(sortMode)) {  
                             retVal = method2.invoke(obj2).toString().compareTo(method1.invoke(obj1).toString()); // 倒序  
                         } else {  
                             retVal = method1.invoke(obj1).toString().compareTo(method2.invoke(obj2).toString()); // 正序  
                         }
                    }
                } catch (Exception e) {
                	log.error("排序有误:"+e);
                    throw new RuntimeException();  
                }  
                return retVal;  
            }  
        });  
    }
    
//    public static void main(String []args){
//    	List<String> list=new ArrayList<>();
//    	list.add("#3");
//    	list.add("#1");
//    	list.add("#2");
//    	Collections.sort(list);
//    	System.out.println();
//    }
	/**
	 * 
	 * @param listMap 源List 
	 * @param key 要排序的map的key
	 * @param flag 0代表正序，1代表倒序
	 * @return 0代表失败
	 */
	  public static int ColllectionSort(List<Map<String,String>> listMap,final String key,final String flag){
		  Collections.sort(listMap,new Comparator<Map<String,String>>() {
				@Override
				public int compare(Map<String,String> o1, Map<String,String> o2) {
					// TODO Auto-generated method stub
					try {
						if("0".equals(flag)) {
							return o1.get(key).toString().compareTo(o2.get(key).toString());
						}else {
							return o2.get(key).toString().compareTo(o1.get(key).toString());
						}
					} catch (Exception e) {
						log.error("排序有误:"+e);
						return 0;
					}
				}
		    	   
			});
		  return 0;
	  }
}