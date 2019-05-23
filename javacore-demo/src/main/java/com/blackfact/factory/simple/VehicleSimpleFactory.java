package com.blackfact.factory.simple;

public class VehicleSimpleFactory {
// 简单工厂的实现
//    public Vehicle create(String name){
//        if("bike".equals(name)){
//            return new Bike();
//        }else if ("bus".equals(name)){
//            return new Bus();
//        }else {
//            return null;
//        }
//    }
// 进行简单优化
//    public Vehicle create(String className){
//        try{
//            if(!(null == className || "".equals(className))){
//                return (Vehicle) Class.forName(className).newInstance();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
    // 进一步的优化
    public Vehicle create(Class<? extends Vehicle> clazz){
        try{
            if(null != clazz ){
                return clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
