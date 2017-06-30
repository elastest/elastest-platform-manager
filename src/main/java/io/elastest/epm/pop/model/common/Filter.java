package io.elastest.epm.pop.model.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter {

  List<Predicate> predicates;

  public Filter() {}

  public List<Predicate> getPredicates() {
    return predicates;
  }

  public void setPredicates(List<Predicate> predicates) {
    this.predicates = predicates;
  }

  public List filter(List list) {
    List filteredList = new ArrayList<>();
    for (Predicate predicate : predicates) {
      filteredList.addAll((List) list.stream().filter(predicate).collect(Collectors.toList()));
      list.removeAll(filteredList);
    }
    return filteredList;
  }
}
