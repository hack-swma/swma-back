package com.swma.swma.domain.user.entity.type;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LanguageType {
	CHINESE, JAPANESE, KOREAN, ENGLISH, FRENCH, GERMAN;
}
