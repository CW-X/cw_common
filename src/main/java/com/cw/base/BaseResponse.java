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
public class BaseResponse<ResponseEntity> {

	private String code = "0";
	private String msg;
	private String traceId;
	private String sysMsg;
	private RequestPagination requestPagination;
	private ResponseEntity responseEntity;
}
