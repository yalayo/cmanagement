(ns cmanagement.compounds.views
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cmanagement.compounds.events :as events]
            [cmanagement.core.components :as c]))

(defn register-compound [{:keys [navigation] :as props}]
  (let [default {:name "" :address ""}
        compound-data (reagent/atom default)]
    (fn []
      (let [{:keys [name address]} @compound-data]
        [c/safe-area-view {:flex 1}
         [c/view {:flex 0.1}
          [c/text {:style {:font-size 30 :align-self :center}} "Register compound"]]
         [c/view {:flex 0.9}
                                        ;(when (:login errors)
                                        ;[errors-list (:login errors)])
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Name" :default-value name :on-change-text #(swap! compound-data assoc :name  %)}]
          [c/text-input {:style {:margin-horizontal 20 :margin-vertical 10} :placeholder "Address" :default-value address :secureTextEntry true :on-change-text #(swap! credentials assoc :address %)}]
          [c/button {:style {} :label "Save" :on-press #(do (re-frame/dispatch [:register-compound @compound-data navigation]))}]]]))))



                                        ;(defn dash-board []
                                        ;  [safe-area-view {:flexDirection "row" :padding 20}
                                        ;   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
                                        ;    [text "TOTAL INGRESOS"]
                                        ;    [text {:style {:font-size 18 :color "blue"}} "$32575.00"]]
                                        ;   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
                                        ;    [text "TOTAL GASTOS"]
                                        ;    [text {:style {:font-size 18 :color "blue"}} "$20590.00"]]
                                        ;   [view {:flex 1 :align-items "center" :justify-content "center" :borderWidth 1 :borderRadius 10}
                                        ;    [text "GANANCIA TOTAL"]
                                        ;    [text {:style {:font-size 18 :color "blue"}} "$17100.00"]]])
