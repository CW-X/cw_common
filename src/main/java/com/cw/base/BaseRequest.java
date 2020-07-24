/**
 * 
 */
package com.cw.base;

import lombok.Data;

/**
 *
 * @author  xueyongfei
 * @date 2020年7月24日
 */
@Data
public class BaseRequest <RequestEntity>{
	private RequestHeader requestHeader;
	private RequestEntity requestEntity;
}
