package com.encryptmail.email.data.db.model

data class UserInfo
constructor(var sub: String,
            var name: String,
            var given_name: String,
            var family_name: String,
            var profile: String,
            var picture: String,
            var email: String,
            var email_verified: Boolean,
            var locale: String
)

