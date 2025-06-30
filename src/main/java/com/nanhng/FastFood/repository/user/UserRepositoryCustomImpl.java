package com.nanhng.FastFood.repository.user;



import com.nanhng.FastFood.dto.request.user.UserRegisterReq;
import com.nanhng.FastFood.dto.response.user.UserListRes;
import com.nanhng.FastFood.entity.user.QUser;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import static com.nanhng.FastFood.util.Constant.PAGE_SIZE;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl extends BaseRepository implements UserRepositoryCustom {

    private final QUser qUser = QUser.user;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public User saveUser(UserRegisterReq request) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(request)
        return null;
    }

    @Override
    public User loginByUsername(String username) {
        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qUser.username.eq(username));
        builder.and(qUser.deleted.eq(false));

        return query.from(qUser)
                .where(builder)
                .select(qUser)
                .fetchOne();
    }

    @Override
    public List<UserListRes> getAllProduct(int page, String keyword) {

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qUser.deleted.eq(false));
        if (keyword != null && !keyword.isBlank()) {
            builder.andAnyOf(qUser.username.containsIgnoreCase(keyword), qUser.email.containsIgnoreCase(keyword));
        }

        return query.from(qUser)
                .where(builder)
                .select(Projections.fields(UserListRes.class,
                        qUser.username,
                        qUser.email,
                        qUser.phone,
                        qUser.role,
                        qUser.id))
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE)
                .fetch();
    }

    @Override
    public long totalRecord(String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qUser.deleted.eq(false));
        if (keyword != null && !keyword.isBlank()) {
            builder.andAnyOf(qUser.username.containsIgnoreCase(keyword), qUser.email.containsIgnoreCase(keyword));
        }

        Long res = query().from(qUser)
                .where(builder)
                .select(qUser.id.count())
                .fetchFirst();
        return res == null ? 0 : res;
    }
}
