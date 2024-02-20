package com.w08e.data.pub.controller;


import com.w08e.data.pub.dto.BankDto;
import com.w08e.data.pub.service.BankService;
import com.w08e.data.pub.util.Result;
import com.w08e.data.pub.vo.BankVo;
import com.w08e.data.pub.vo.QueryResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* 开户行
* @Author: 包包大人
* @Date: 2023/3/27
*/

@RestController
@AllArgsConstructor
@RequestMapping("/bank")
public class BankController {

	private final BankService bankService;


	/**
	 *  查询开户行
	 */
	@CrossOrigin(origins  ="*",allowCredentials = "true")
	@GetMapping
	public Result<QueryResult<BankVo>> list(BankDto bankDto) {
		return Result.success(bankService.queryBank(bankDto));
	}


}
