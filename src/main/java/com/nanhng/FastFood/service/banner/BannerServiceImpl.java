package com.nanhng.FastFood.service.banner;

import com.nanhng.FastFood.dto.constant.RoleType;
import com.nanhng.FastFood.dto.request.banner.AddBannerReq;
import com.nanhng.FastFood.dto.request.banner.UpdateBannerReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.dto.response.BaseResponse;
import com.nanhng.FastFood.dto.response.category.CategoryListRes;
import com.nanhng.FastFood.entity.banner.Banner;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.banner.BannerRepository;
import com.nanhng.FastFood.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImpl extends BaseService implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public Banner add(AddBannerReq request) {
        User user = getUser(RoleType.ADMIN);
        Banner banner = new Banner();

        banner.setImageId(request.getImageId());
        banner.setLink(request.getLink());
        return bannerRepository.save(banner);
    }

    @Override
    public Banner update(UpdateBannerReq request) {
        User user = getUser(RoleType.ADMIN);

        Banner banner = bannerRepository.findByIdToUpdate(request.getId());
        if (banner == null) {
            throw new LovelyException("Không tìm thấy danh mục", HttpStatus.NOT_FOUND);
        }
        if (request.getImageId() != null) {
            banner.setImageId(request.getImageId());
        }
        if (request.getLink() != null && !request.getLink().isEmpty()) {
            banner.setLink(request.getLink());
        }
        return bannerRepository.save(banner);
    }

    @Override
    public List<Integer> delete(IdsRequest request) {
        List<Integer> ids = request.getIds();
        List<Integer> existIds = bannerRepository.getExistIds(ids);
        List<Integer> notExistId = ids.stream().filter(id -> !existIds.contains(id)).toList();
        if (!notExistId.isEmpty()) {
            throw new LovelyException("Không tìm thấy id");
        }
        bannerRepository.deleteByIds(ids);
        return existIds;
    }

    @Override
    public BaseResponse<List<Banner>> getList() {
        List<Banner> list = bannerRepository.getList();
        return new BaseResponse<>(list);
    }
}
