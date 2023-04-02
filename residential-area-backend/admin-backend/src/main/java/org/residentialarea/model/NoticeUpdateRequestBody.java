package org.residentialarea.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeUpdateRequestBody extends NoticeCreateRequestBody{
    private int id;
}
