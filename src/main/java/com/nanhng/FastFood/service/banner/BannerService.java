package com.nanhng.FastFood.service.banner;

import com.nanhng.FastFood.dto.request.banner.AddBannerReq;
import com.nanhng.FastFood.dto.request.banner.UpdateBannerReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.entity.banner.Banner;

import java.util.List;

public interface BannerService {
    Banner add (AddBannerReq request);
    Banner update (UpdateBannerReq request);
    List<Integer> delete (IdsRequest request);
    BaseResponse<List<Banner>> getList(int page);
}
