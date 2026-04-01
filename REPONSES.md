Question (Ex1) : Une méthode default dans une interface peut-elle accéder aux champs privés de la classe qui l'implémente ? Justifiez, en vous appuyant sur isDefective() dans Qualifiable et getQualityScore() dans Duck.

Non, une méthode default dans une interface ne peut pas accéder directement aux champs privés d'une classe qui l'implémente. Les interfaces ne connaissent pas les détails d'implémentation des classes qui les implémentent.  Dans le cas de isDefective() dans Qualifiable et getQualityScore() dans Duck, la méthode isDefective() appelle uniquement la méthode publique getQualityScore() définie dans l'interface Qualifiable. Cela respecte le principe d'encapsulation, car isDefective() n'accède pas directement au champ privé qualityScore de la classe Duck.

Question (Ex2) : Dans ce projet, Maintainable est une interface et Machine est une classe abstraite. Quelle règle Java vous aurait empêché de faire l'inverse (rendre Maintainable abstraite et Machine une interface) ? Plus généralement, quand choisit-on une interface plutôt qu'une classe abstraite ?

Une interface comme `Maintainable` ne peut pas contenir de champs d'état ou de logique d'implémentation complexe, ce qui est nécessaire pour une classe comme `Machine`. En Java, une interface est utilisée pour définir un contrat (ensemble de méthodes) que les classes doivent implémenter, tandis qu'une classe abstraite peut contenir des champs, des constructeurs et des méthodes partiellement implémentées.

Dans ce projet, `Machine` a des champs (comme `condition`, `capacity`, etc.) et des méthodes concrètes qui manipulent ces champs (`degrade()`, `maintain()`, etc.). Ces fonctionnalités ne peuvent pas être définies dans une interface. En revanche, `Maintainable` est une interface car elle définit un comportement générique que plusieurs classes peuvent partager sans imposer de structure d'état.

On choisit une interface lorsqu'on veut définir un contrat commun sans partager d'implémentation ou d'état. Une classe abstraite est utilisée lorsqu'on veut partager une implémentation partielle ou des champs entre plusieurs sous-classes.

