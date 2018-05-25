package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	
	@RequestMapping("list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0")long parentId){
	
		List<EUTreeNode> resultList=contentCategoryService.getCategoryList(parentId);
		return resultList;
		
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId,String name){
		
		TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id){
		
		TaotaoResult result = contentCategoryService.deleteContentCategory(id);
		return result.ok();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id,String name){
		
		TaotaoResult result = contentCategoryService.updateContentCategory(id,name);
		return result.ok();
	}
	
}
