package com.example.usersanalytical.data.mapper

import com.example.usersanalytical.data.database.UsersDbModel
import com.example.usersanalytical.domain.model.Users
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapDbModelToEntity(usersDbModel: UsersDbModel?) = Users(
        id = usersDbModel?.id ?: 0,
        name = usersDbModel?.name ?: "",
        year_old = usersDbModel?.year_old ?: "",
        hemoglobin = usersDbModel?.hemoglobin ?: "",
        erythrocytes = usersDbModel?.erythrocytes ?: "",
        platelets = usersDbModel?.platelets ?: "",
        lymphocytes = usersDbModel?.lymphocytes ?: "",
        monocytes = usersDbModel?.monocytes ?: "",
        basophils = usersDbModel?.basophils ?: ""
    )
}