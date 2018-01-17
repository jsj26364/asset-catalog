package info.jsjackson.catalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import info.jsjackson.catalog.domain.Asset;
import info.jsjackson.catalog.services.AssetService;

@Controller
public class AssetController {

	private AssetService assetService;

	@Autowired
	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}

	@RequestMapping("asset/new")
	public String newAsset(Model model) {
		model.addAttribute("asset", new Asset());
		return "assetform";
	}

	@RequestMapping(value = "asset", method = RequestMethod.POST)
	public String saveAsset(Asset asset) {
		assetService.saveAsset(asset);
		return "redirect:/asset/" + asset.getId();
	}

	@RequestMapping("asset/{id}")
	public String showAsset(@PathVariable Integer id, Model model) {
		model.addAttribute("asset", assetService.getAssetById(id));
		return "assetshow";
	}

	@RequestMapping(value = "/assets", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("assets", assetService.listAllAssets());
		return "assets";
	}

	@RequestMapping("asset/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("asset", assetService.getAssetById(id));
		return "assetform";
	}

	@RequestMapping("asset/delete/{id}")
	public String delete(@PathVariable Integer id) {
		assetService.deleteAssetById(id);
		return "redirect:/assets";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
	     return "login";
	}
	
}
