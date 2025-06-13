package com.nanhng.FastFood.repository.address;

import com.nanhng.FastFood.entity.address.Address;
import com.nanhng.FastFood.entity.address.QAddress;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;

public class AddressRepositoryCustomImpl extends BaseRepository implements AddressRepositoryCustom{

    private final QAddress qAddress = QAddress.address;

    @Override
    public Address findByIdToUpdate(Integer id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAddress.id.eq(id));
        builder.and(qAddress.deleted.eq(false));

        return query().from(qAddress)
                .where(builder)
                .select(qAddress)
                .fetchOne();
    }

    @Override
    @Transactional
    public Address addNew(Address address) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAddress.deleted.eq(false));
        builder.and(qAddress.userId.count().lt(6));

        return query(
    }
}
