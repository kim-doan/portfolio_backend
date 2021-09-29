package com.portfolio.main.response.service;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.portfolio.main.response.model.CommonResult;
import com.portfolio.main.response.model.ListResult;
import com.portfolio.main.response.model.LoginResult;
import com.portfolio.main.response.model.PageListResult;
import com.portfolio.main.response.model.SingleResult;

@Service
public class ResponseService {
	
    // enum으로 api 요청 결과에 대한 code, message를 정의합니다.
    public enum CommonResponse {
        SUCCESS("0", "성공하였습니다.");

        String code;
        String msg;

        CommonResponse(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
    // 단일건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        List<String> fieldType = new ArrayList<String>(); // 결과값 리스트 객체 필드의 자료형 묶음
        List<String> fieldName = new ArrayList<String>(); // 결과값 리스트 객체 필드명
        
        for(Field field : data.getClass().getDeclaredFields()) {
        	fieldType.add(field.getType().getSimpleName());
        	fieldName.add(field.getName());
        }
        
        result.setRowSize(1);
        result.setFieldName(fieldName);
        result.setFieldType(fieldType);
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    
    public <T> ListResult<T> getListResult(Class c, List<T> list)  {
    	
    	if(list.size() > 0 ) {
        	return getListResult(list);    		
    	} else {
    		try {
				T object = (T) c.newInstance();
	        	return getListResult(object);    					
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
    	}
    }
    
    public <T> PageListResult<T> getPageListResult(Class c, Page<T> page)  {
    	
    	List<T> list = page.getContent();
    	
    	if(list.size() > 0 ) {
        	return getPageListResult(page);    		
    	} else {
    		try {
				T object = (T) c.newInstance();
	        	return getPageListResult(object);    					
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
    	}
    }
    
    
    // 다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        List<String> fieldType = new ArrayList<String>(); // 결과값 리스트 객체 필드의 자료형 묶음
        List<String> fieldName = new ArrayList<String>(); // 결과값 리스트 객체 필드명
        if(list.size()>0)
        {
        for(Field field : list.get(0).getClass().getDeclaredFields()) {
        	fieldType.add(field.getType().getSimpleName());
        	fieldName.add(field.getName());
        }
        }
        result.setRowSize(list.size());
        result.setList(list);
        result.setFieldType(fieldType);
        result.setFieldName(fieldName);
        
        setSuccessResult(result);
        return result;
    }
    
    // 다중건 결과를 처리하는 메소드(페이징)
    public <T> PageListResult<T> getPageListResult(Page<T> page) {
        PageListResult<T> result = new PageListResult<>();
        List<String> fieldType = new ArrayList<String>(); // 결과값 리스트 객체 필드의 자료형 묶음
        List<String> fieldName = new ArrayList<String>(); // 결과값 리스트 객체 필드명
        
        List<T> list = page.getContent();
        
        for(Field field : list.get(0).getClass().getDeclaredFields()) {
        	fieldType.add(field.getType().getSimpleName());
        	fieldName.add(field.getName());
        }
        
        result.setRowSize(list.size());
        result.setData(list);
        result.setFieldType(fieldType);
        result.setFieldName(fieldName);
        result.setTotalCount(page.getTotalElements());
        result.setTotalPages(page.getTotalPages());
        
        setSuccessResult(result);
        return result;
    }
    
    // 0건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(T zeroObjName) {
        ListResult<T> result = new ListResult<>();
        List<String> fieldType = new ArrayList<String>(); // 결과값 리스트 객체 필드의 자료형 묶음
        List<String> fieldName = new ArrayList<String>(); // 결과값 리스트 객체 필드명
        
        for(Field field : zeroObjName.getClass().getDeclaredFields()) {
        	fieldType.add(field.getType().getSimpleName());
        	fieldName.add(field.getName());
        }
        
        result.setRowSize(0);
        result.setList(new ArrayList<T>());
        result.setFieldType(fieldType);
        result.setFieldName(fieldName);
        
        setSuccessResult(result);
        return result;
    }
    
    // 0건 결과를 처리하는 메소드(페이징)
    public <T> PageListResult<T> getPageListResult(T zeroObjName) {
        PageListResult<T> result = new PageListResult<>();
        List<String> fieldType = new ArrayList<String>(); // 결과값 리스트 객체 필드의 자료형 묶음
        List<String> fieldName = new ArrayList<String>(); // 결과값 리스트 객체 필드명
        
        for(Field field : zeroObjName.getClass().getDeclaredFields()) {
        	fieldType.add(field.getType().getSimpleName());
        	fieldName.add(field.getName());
        }
        
        result.setRowSize(0);
        result.setData(new ArrayList<T>());
        result.setFieldType(fieldType);
        result.setFieldName(fieldName);
        result.setTotalCount(0);
        result.setTotalPages(0);
        
        setSuccessResult(result);
        return result;
    }
    
    //칼럼정보 없이 리스트 결과를 처리하는 메소드
    public <T> ListResult<T> getListSimpleResult(List<T> list)  {
    	
        ListResult<T> result = new ListResult<>();
        result.setRowSize(list.size());
        result.setList(list);        
        setSuccessResult(result);
        return result;
    }
    
    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    
    // 로그인 결과만 처리하는 메소드
    public LoginResult getLoginResult(String token) {
        LoginResult result = new LoginResult();
        setLoginSuccessResult(result, token);
        return result;
    }
    
    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult(String code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    
    // 로그인 실패 결과만 처리하는 메소드
    public LoginResult getLoginFailResult(String code, String msg) {
        LoginResult result = new LoginResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
    
    // 로그인 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setLoginSuccessResult(LoginResult result, String token) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
        result.setToken(token);
    }
    
}
