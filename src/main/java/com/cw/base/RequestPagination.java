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
public class RequestPagination {
		private Integer pageNo;
		private Integer pageSize;
		private Integer offset;
		private Integer limit;
		
}
