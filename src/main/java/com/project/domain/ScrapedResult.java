package com.project.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScrapedResult {

    private Company company;

    private List<Dividend> dividendList;

}
